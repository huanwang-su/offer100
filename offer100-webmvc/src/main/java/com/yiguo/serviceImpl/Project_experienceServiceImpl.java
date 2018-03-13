package com.yiguo.serviceImpl;

import com.yiguo.bean.ProjectExperience;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.ProjectExperienceMapper;
import com.yiguo.service.ProjectExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ProjectExperience")
@Transactional
public class Project_experienceServiceImpl extends AbstractBaseServiceImpl<Integer, ProjectExperience> implements ProjectExperienceService {
    @Autowired
    ProjectExperienceMapper dao;

    @Override
    public BaseMapper<Integer, ProjectExperience> getDao() {
        // TODO Auto-generated method stub
        return dao;
    }

    @Override
    public List<ProjectExperience> getAll() {
        return dao.query();
    }

    @Override
    public int findById(Integer id) {
        return dao.findById(id);
    }
}
