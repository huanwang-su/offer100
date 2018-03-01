package com.yiguo.serviceImpl;

import com.yiguo.bean.Education;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.EducationMapper;
import com.yiguo.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Education")
@Transactional
public class EducationServiceImpl extends AbstractBaseServiceImpl<Integer, Education> implements EducationService {
	@Autowired
	EducationMapper dao;

	@Override
	public BaseMapper<Integer, Education> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
