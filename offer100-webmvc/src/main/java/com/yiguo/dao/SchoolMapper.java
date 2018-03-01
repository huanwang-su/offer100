package com.yiguo.dao;

import com.yiguo.bean.School;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SchoolMapper extends BaseMapper<Integer, School> {

}