package com.yiguo.dao;

import com.yiguo.bean.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PermissionMapper extends BaseMapper<Integer, Permission> {

}