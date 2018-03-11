package com.yiguo.service;

import com.yiguo.bean.Project_experience;

import java.util.List;

public interface Project_experienceService extends BaseService<Integer, Project_experience> {

    List<Project_experience> getAll();

    int findById(Integer id);
}
