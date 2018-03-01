package com.yiguo.serviceImpl;

import com.yiguo.bean.Permission;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.PermissionMapper;
import com.yiguo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Permission")
@Transactional
public class PermissionServiceImpl extends AbstractBaseServiceImpl<Integer, Permission> implements PermissionService {
	@Autowired
	PermissionMapper dao;

	@Override
	public BaseMapper<Integer, Permission> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
