package com.yiguo.dao;

import com.yiguo.bean.School;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SchoolMapper extends BaseMapper<Integer, School> {
      List <String > findByZoneId(Integer zoneId);
 }