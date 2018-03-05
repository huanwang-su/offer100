package com.yiguo.offer100.search.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Resource;
import javax.annotation.Resources;

import com.yiguo.offer100.search.bean.Talent;
import com.yiguo.offer100.search.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Talent搜索仓库类
 *
 * @author wanghuan
 * @date 2018-01-18
 */
@Repository
public class TalentSearchRepository {

    @Autowired
    @Qualifier("talentHttpSolrClient")
    private HttpSolrClient httpSolrClient;

    public void save(List<Talent> talents) throws SolrServerException, IOException {
        // 添加多个
        Collection<SolrInputDocument> docs = new ArrayList<>();
        talents.forEach(new Consumer<Talent>() {
            @Override
            public void accept(Talent talent) {
                SolrInputDocument doc = new SolrInputDocument();
                Map<String, Object> map = BeanUtils.objectToMap(talent);
                map.forEach((t, u) -> {
                    if (u != null && !StringUtils.equalsAny(t, "key", "maxAge", "minAge")) {
                        doc.addField(t, u);
                    }
                });
                docs.add(doc);
            }
        });
        httpSolrClient.add(docs);
        httpSolrClient.commit();

    }

    public void save(Talent talent) throws SolrServerException, IOException {
        SolrInputDocument doc = new SolrInputDocument();
        Map<String, Object> map = BeanUtils.objectToMap(talent);
        map.forEach((t, u) -> {
            if (u != null && !StringUtils.equalsAny(t, "key", "maxAge", "minAge")) {
                doc.addField(t, u);
            }
        });
        httpSolrClient.add(doc);
        httpSolrClient.commit();
    }

    public void deleteById(String id) throws SolrServerException, IOException {
        httpSolrClient.deleteById(id);
        httpSolrClient.commit();
    }

    public void deleteByIds(List<String> id) throws SolrServerException, IOException {
        httpSolrClient.deleteById(id);
        httpSolrClient.commit();
    }

    public List<Talent> search(Talent talent, Integer start, Integer size) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        StringBuilder queryStringBuilder = new StringBuilder("*:*");
        StringBuilder fqStringBuilder = new StringBuilder();
        Map<String, Object> map = BeanUtils.objectToMap(talent);
        map.forEach((t, u) -> {
            Optional.ofNullable(u).ifPresent(v -> {
                if (v instanceof List) {
                    List<String> list = (List<String>) v;
                    if (!StringUtils.equals(t, "key")) {
                        list.forEach(l -> queryStringBuilder.append(" AND ").append(t).append(":").append(l));
                    } else {
                        list.forEach(l -> queryStringBuilder.append(" AND ").append("all_text_pinyin").append(":")
                                .append(l));
                    }
                } else {

                    queryStringBuilder.append(" AND ").append(t).append(":").append(v);
                }
            });
        });
        // q 查询字符串，如果查询所有*:*
        query.set("q", queryStringBuilder.toString());
        // fq 过滤条件，过滤是基于查询结果中的过滤
        fqStringBuilder.append("age:[").append(map.get("minAge") == null ? "-1" : map.get("minAge")).append(" TO ")
                .append(map.get("maxAge") == null ? "1000" : map.get("maxAge")).append("]");
        query.set("fq", fqStringBuilder.toString());
        // fq 此过滤条件可以同时搜索出奔驰和宝马两款车型，但是需要给catalogname配置相应的分词器
        // query.set("fq", "catalogname:奔驰宝马");
        // sort 排序，请注意，如果一个字段没有被索引，那么它是无法排序的
        query.set("sort", "rank desc");
        // start row 分页信息，与mysql的limit的两个参数一致效果
        if (start != null && size != null)

        {
            query.setStart(start);
            query.setRows(size);
        }

        // fl 指定返回那些字段内容，用逗号或空格分隔多个
        query.set("fl", "id,expectZones,expectJobs,currentLocation,age,workAge,sex,education,rank");
        return httpSolrClient.query(query).getBeans(Talent.class);
    }

}
