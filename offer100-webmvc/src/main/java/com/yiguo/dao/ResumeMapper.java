package com.yiguo.dao;

import com.yiguo.bean.Resume;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResumeMapper extends BaseMapper<Integer, Resume> {


    List<Resume> query( );

    int findById(Integer id);
}