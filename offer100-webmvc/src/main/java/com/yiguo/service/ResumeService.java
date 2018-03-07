package com.yiguo.service;

import com.yiguo.bean.Resume;

import java.util.List;

public interface ResumeService extends BaseService<Integer, Resume> {

    List<Resume> getAll( );

}
