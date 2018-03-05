package com.yiguo.offer100.search.service;

import com.yiguo.offer100.search.bean.Talent;

import java.util.List;


/**
 * 人才搜索服务接口
 *
 * @author wanghuan
 * @date 2018-01-18
 */
public interface TalentSearchService {
    
    /**
     * 保存岗位
     * @param talent
     */
    void saveTalent(Talent talent);
    
    /**
     * 保存岗位
     * @param talents
     */
    void saveTalents(List<Talent> talents);
    
    /**
     * 搜索岗位
     * 
     * 调用的时候注意:
     * 1. 等值查询：除了key中的字段，其他均为等值查询
     * 2. 对于list，list中的每一个字段都满足搜索引擎中的数据
     * @param talent
     * @param start
     * @param end
     */
    List<Talent> search(Talent talent, int start, int end);
    
    /**
     * 删除岗位
     * @param id
     */
    void deleteById(String id);

    void deleteById(List<String> id);
    
}
