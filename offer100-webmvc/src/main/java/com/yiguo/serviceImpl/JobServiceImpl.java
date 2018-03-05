package com.yiguo.serviceImpl;

import com.yiguo.bean.Job;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.JobMapper;
import com.yiguo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("Job")
@Transactional
public class JobServiceImpl extends AbstractBaseServiceImpl<Integer, Job> implements JobService {
	@Autowired
	JobMapper dao;

	@Override
	public BaseMapper<Integer, Job> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public List<Job> querySearch(Integer enterpriseId) {
		// TODO Auto-generated method stub
		return dao.querySearch(enterpriseId);
	}

	@Override
	public int selectByIds(Job job) {
		return dao.selectByIds(job);
	}

}
