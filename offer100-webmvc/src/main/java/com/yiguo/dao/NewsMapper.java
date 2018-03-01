package com.yiguo.dao;

import com.yiguo.bean.News;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NewsMapper extends BaseMapper<Integer, News> {

}