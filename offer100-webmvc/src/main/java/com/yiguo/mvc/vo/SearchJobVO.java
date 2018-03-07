package com.yiguo.mvc.vo;

import com.yiguo.offer100.search.bean.Job;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Job实体类
 * 
 * @author liyue
 * @date 2018-01-23
 */
public class SearchJobVO extends Job {
	private com.yiguo.bean.Job job;

    public com.yiguo.bean.Job getJob() {
        return job;
    }

    public void setJob(com.yiguo.bean.Job job) {
        this.job = job;
    }
}