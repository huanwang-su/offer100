package com.yiguo.dao;

import com.yiguo.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleMapper extends BaseMapper<Integer, Role> {

}