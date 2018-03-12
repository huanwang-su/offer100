package com.yiguo.dao;

import com.yiguo.bean.Education;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EducationMapper extends BaseMapper<Integer, Education> {
    int selectByIds(Integer userId);
    List<Education> getEducationByUserId(Integer userId);

}