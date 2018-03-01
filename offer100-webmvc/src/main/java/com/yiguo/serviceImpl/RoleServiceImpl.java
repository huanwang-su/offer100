package com.yiguo.serviceImpl;

import com.google.common.collect.Sets;
import com.yiguo.bean.Role;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.RoleMapper;
import com.yiguo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service("Role")
@Transactional
public class RoleServiceImpl extends AbstractBaseServiceImpl<Integer, Role> implements RoleService {
	@Autowired
	RoleMapper dao;

	@Override
	public Set<String> findPermissions(String username) {
		// TODO Auto-generated method stub
		return Sets.newHashSet("resourcepre:resource:update", "resourcepre:resource:create");
	}

	@Override
	public Set<String> findRoles(String username) {
		// TODO Auto-generated method stub
		return Sets.newHashSet("admin", "role");
	}

	@Override
	public BaseMapper<Integer, Role> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
