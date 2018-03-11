package com.yiguo.service;

import com.yiguo.bean.Job;
import com.yiguo.offer100.search.bean.SearchJob;

import java.util.List;

public interface JobService extends BaseService<Integer, Job> {
	List<Job> querySearch(Integer enterpriseId);
	int selectByIds(Job job);
	SearchJob toSolrJob(Job src);
}
