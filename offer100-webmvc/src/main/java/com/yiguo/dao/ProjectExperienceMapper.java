package com.yiguo.dao;

import com.yiguo.bean.ProjectExperience;

import java.util.List;

public interface ProjectExperienceMapper extends BaseMapper<Integer, ProjectExperience> {

    List<ProjectExperience> query( );

    int findById(Integer id);
}
