package com.yiguo.serviceImpl;

import com.yiguo.bean.Resume;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.ResumeMapper;
import com.yiguo.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Resume")
@Transactional
public class ResumeServiceImpl extends AbstractBaseServiceImpl<Integer, Resume> implements ResumeService {
	@Autowired
	ResumeMapper dao;

	@Override
	public BaseMapper<Integer, Resume> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
