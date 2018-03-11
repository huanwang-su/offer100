package com.yiguo.dao;

import com.yiguo.bean.Project_experience;
import com.yiguo.dao.BaseMapper;

import java.util.List;

public interface Project_experienceMapper extends BaseMapper<Integer, Project_experience> {

    List<Project_experience> query( );

    int findById(Integer id);
}
