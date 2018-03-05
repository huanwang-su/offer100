package com.yiguo.service;

import com.yiguo.bean.Resume_post_record;

public interface Resume_post_recordService extends BaseService<Integer, Resume_post_record> {
	int selectjob(Integer enterpriseId);
	int selectjob1(Integer enterpriseId);
}
