package com.yiguo.service;

import com.yiguo.bean.School;

import java.util.List;

public interface SchoolService extends BaseService<Integer, School> {
    List<String > findByZoneId(Integer zoneId);
}
