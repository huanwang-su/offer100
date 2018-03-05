package com.yiguo.dao;

import com.yiguo.bean.Education;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EducationMapper extends BaseMapper<Integer, Education> {
    int selectByIds(Integer userId);
}