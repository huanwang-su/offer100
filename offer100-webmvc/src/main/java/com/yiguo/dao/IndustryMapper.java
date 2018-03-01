package com.yiguo.dao;

import com.yiguo.bean.Industry;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IndustryMapper extends BaseMapper<Integer, Industry> {

}