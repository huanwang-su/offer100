package com.yiguo.serviceImpl;

import com.yiguo.bean.Resume_post_record;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.Resume_post_recordMapper;
import com.yiguo.service.Resume_post_recordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Resume_post_record")
@Transactional
public class Resume_post_recordServiceImpl extends AbstractBaseServiceImpl<Integer, Resume_post_record>
		implements Resume_post_recordService {
	@Autowired
	Resume_post_recordMapper dao;

	@Override
	public BaseMapper<Integer, Resume_post_record> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public int selectjob(Integer jobId) {
		// TODO Auto-generated method stub
		return dao.selectjob(jobId);
	}

	@Override
	public int selectjob1(Integer jobId) {
		// TODO Auto-generated method stub
		return dao.selectjob1(jobId);
	}

}
