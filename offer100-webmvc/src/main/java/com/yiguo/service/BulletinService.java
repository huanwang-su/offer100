package com.yiguo.service;

import com.yiguo.bean.Bulletin;

import java.util.List;

public interface BulletinService extends BaseService<Integer, Bulletin> {

    List<Bulletin> getAll();

    int findById(Integer id);

}
