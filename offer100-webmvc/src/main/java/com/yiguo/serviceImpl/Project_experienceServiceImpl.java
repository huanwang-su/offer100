package com.yiguo.serviceImpl;

import com.yiguo.bean.Project_experience;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.Project_experienceMapper;
import com.yiguo.service.Project_experienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("Project_experience")
@Transactional
public class Project_experienceServiceImpl extends AbstractBaseServiceImpl<Integer, Project_experience> implements Project_experienceService {
    @Autowired
    Project_experienceMapper dao;

    @Override
    public BaseMapper<Integer, Project_experience> getDao() {
        // TODO Auto-generated method stub
        return dao;
    }

    @Override
    public List<Project_experience> getAll() {
        return dao.query();
    }

    @Override
    public int findById(Integer id) {
        return dao.findById(id);
    }
}
