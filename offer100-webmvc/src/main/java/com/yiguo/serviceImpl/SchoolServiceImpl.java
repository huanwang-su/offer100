package com.yiguo.serviceImpl;

import com.yiguo.bean.School;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.SchoolMapper;
import com.yiguo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("School")
@Transactional
public class SchoolServiceImpl extends AbstractBaseServiceImpl<Integer, School> implements SchoolService {
	@Autowired
	SchoolMapper dao;

	@Override
	public BaseMapper<Integer, School> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
