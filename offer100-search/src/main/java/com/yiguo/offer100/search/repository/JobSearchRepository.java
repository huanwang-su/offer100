package com.yiguo.offer100.search.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.offer100.search.bean.SearchJob;
import com.yiguo.offer100.search.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Job搜索仓库类
 *
 * @author wanghuan
 * @date 2018-01-18
 */
@Repository
public class JobSearchRepository {

    @Autowired
    @Qualifier("jobHttpSolrClient")
    private HttpSolrClient httpSolrClient;

    public void save(List<SearchJob> searchJobs) throws SolrServerException, IOException {
        // 添加多个
        Collection<SolrInputDocument> docs = new ArrayList<>();
        searchJobs.forEach(job -> {
            SolrInputDocument doc = new SolrInputDocument();
            Map<String, Object> map = BeanUtils.objectToMap(job);
            map.forEach((t, u) -> {
                if (u != null && !StringUtils.equals(t, "key")) {
                    doc.addField(t, u);
                }
            });
            docs.add(doc);
        });
        httpSolrClient.add(docs);
        httpSolrClient.commit();

    }

    public void save(SearchJob searchJob) throws SolrServerException, IOException {
        SolrInputDocument doc = new SolrInputDocument();
        Map<String, Object> map = BeanUtils.objectToMap(searchJob);
        map.forEach((t, u) -> {
            if (u != null && !StringUtils.equals(t, "key")) {
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

    public PageInfo<SearchJob> search(SearchJob searchJob, PageInfo<SearchJob> pageInfo, String sortKey, Boolean asc) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        StringBuilder queryStringBuilder = new StringBuilder("*:*");
        Map<String, Object> map = BeanUtils.objectToMap(searchJob);
        map.forEach((t, u) -> {
            Optional.ofNullable(u).ifPresent(v -> {
                if (v instanceof List) {
                    List<String> list = (List<String>) v;
                    if (!StringUtils.equals(t, "key")) {
                        list.forEach(l -> queryStringBuilder.append(" AND ").append(t).append(":").append(l));
                    } else {
                        list.forEach(l -> queryStringBuilder.append(" AND ").append("all_text").append(":").append(l));
                    }
                } else {
                    if (StringUtils.equalsAny(t, "title", "enterprise")) {
                        queryStringBuilder.append(" AND ").append(t).append("_pinyin").append(":").append(v);
                    } else {
                        queryStringBuilder.append(" AND ").append(t).append(":").append(v);
                    }
                }
            });
        });

        // q 查询字符串，如果查询所有*:*
        query.set("q", queryStringBuilder.toString());
        // fq 过滤条件，过滤是基于查询结果中的过滤
        // query.set("fq", "catalogname:*驰*");
        // fq 此过滤条件可以同时搜索出奔驰和宝马两款车型，但是需要给catalogname配置相应的分词器
        // query.set("fq", "catalogname:奔驰宝马");
        // sort 排序，请注意，如果一个字段没有被索引，那么它是无法排序的
        //query.setParam("stats=true&stats.field=id")
        //query.set("sort", "rank desc");
        if(sortKey!=null){
            query.setSort(sortKey, asc!=null&&!asc?SolrQuery.ORDER.desc:SolrQuery.ORDER.asc);
        }
        else
            query.setSort("rank", SolrQuery.ORDER.desc);
        query.setStart(pageInfo.getStart()).setRows(pageInfo.getPageSize());
        query.setParam("stats",true);
        query.setParam("stats.field","id");
        // fl 指定返回那些字段内容，用逗号或空格分隔多个
        query.setFields("id","enterprise","enterpriseId","title","nature","zone","category","wage","enterpriseLogo","education",
                "rank","publishTime","serviceYear","peopleNumber","enterpriseCategory","welfare");
        QueryResponse response = httpSolrClient.query(query);
        pageInfo.setRows(response.getBeans(SearchJob.class));
        pageInfo.setTotal(response.getFieldStatsInfo().get("id").getCount().intValue());
        return pageInfo;
    }

}
