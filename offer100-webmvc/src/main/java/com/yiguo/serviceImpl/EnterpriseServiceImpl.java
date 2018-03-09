package com.yiguo.serviceImpl;

import com.yiguo.bean.Enterprise;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.EnterpriseMapper;
import com.yiguo.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Enterprise")
@Transactional
public class EnterpriseServiceImpl extends AbstractBaseServiceImpl<Integer, Enterprise> implements EnterpriseService {
	@Autowired
	EnterpriseMapper dao;

	@Override
	public BaseMapper<Integer, Enterprise> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}


	@Override
	public Enterprise selectByIds(String userName, String userPassword) {
		return dao.selectByIds(userName,userPassword);
	}

	@Override
	public Enterprise findByUsername(String username) {
		return dao.findByUsername(username);
	}
}
