package com.yiguo.dao;

import com.yiguo.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleMapper extends BaseMapper<Integer, Role> {

}