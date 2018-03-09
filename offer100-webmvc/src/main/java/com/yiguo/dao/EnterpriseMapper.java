package com.yiguo.dao;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EnterpriseMapper extends BaseMapper<Integer, Enterprise> {
    Enterprise selectByIds(String userName,String userPassword);
    Enterprise findByUsername(String userName);
}