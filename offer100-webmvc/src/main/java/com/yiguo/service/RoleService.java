package com.yiguo.service;

import com.yiguo.bean.Role;

import java.util.Set;


public interface RoleService extends BaseService<Integer, Role> {
	Set<String> findPermissions(String username);

	Set<String> findRoles(String username);
}
