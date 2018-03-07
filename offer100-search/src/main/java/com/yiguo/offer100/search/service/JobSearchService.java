package com.yiguo.offer100.search.service;

import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.offer100.search.bean.Job;

import java.util.List;


/**
 * 岗位搜索服务接口
 *
 * @author wanghuan
 * @date 2018-01-18
 */
public interface JobSearchService {
    
    /**
     * 保存岗位
     * @param job
     */
    void saveJob(Job job);
    
    /**
     * 保存岗位
     * @param jobs
     */
    void saveJobs(List<Job> jobs);
    
    /**
     * 搜索岗位
     * 调用的时候注意:
     * 1. 等值查询：除了key中的字段，其他均为等值查询
     * 2. 对于list，list中的每一个字段都满足搜索引擎中的数据
     * @param job
     * @param start
     * @param size
     */
    PageInfo<Job> search(Job job, PageInfo<Job> pageInfo,String sortKey, Boolean asc);
    
    /**
     * 删除岗位
     * @param id
     */
    void deleteById(String id);

    void deleteById(List<String> id);
    
}
