package com.yiguo.service;

import com.yiguo.bean.Education;

import java.util.List;

public interface EducationService extends BaseService<Integer, Education> {
    int selectByIds(Integer userId);
    List<Education> getEducationByUserId(Integer userId);
}
