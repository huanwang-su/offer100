package com.yiguo.mvc.vo;

import com.yiguo.offer100.search.bean.SearchJob;

/**
 * 
 * Job实体类
 * 
 * @author liyue
 * @date 2018-01-23
 */
public class SearchJobVO extends SearchJob {
	private com.yiguo.bean.Job job;

    public com.yiguo.bean.Job getJob() {
        return job;
    }

    public void setJob(com.yiguo.bean.Job job) {
        this.job = job;
    }
}