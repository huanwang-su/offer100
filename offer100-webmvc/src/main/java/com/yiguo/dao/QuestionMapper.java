package com.yiguo.dao;

import com.yiguo.bean.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionMapper extends BaseMapper<Integer, Question> {

}