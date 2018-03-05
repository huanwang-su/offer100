package com.yiguo.service;

import com.yiguo.bean.Job;

import java.util.List;

public interface JobService extends BaseService<Integer, Job> {
	List<Job> querySearch(Integer enterpriseId);
	int selectByIds(Job job);
}
