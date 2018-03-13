package com.yiguo.service;

import com.yiguo.bean.ProjectExperience;

import java.util.List;

public interface ProjectExperienceService extends BaseService<Integer, ProjectExperience> {

    List<ProjectExperience> getAll();

    int findById(Integer id);
}
