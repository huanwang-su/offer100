package com.yiguo.service;

import com.yiguo.bean.Education;

public interface EducationService extends BaseService<Integer, Education> {
    int selectByIds(Integer userId);

}
