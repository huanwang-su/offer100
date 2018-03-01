package com.yiguo.dao;

import com.yiguo.bean.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
 @Mapper
public interface AnswerMapper extends BaseMapper<Integer, Answer> {

}