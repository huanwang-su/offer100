package com.yiguo.dao;

import com.yiguo.bean.Resume;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ResumeMapper extends BaseMapper<Integer, Resume> {

}