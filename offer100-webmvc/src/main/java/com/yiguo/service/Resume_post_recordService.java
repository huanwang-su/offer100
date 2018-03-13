package com.yiguo.service;

import com.yiguo.bean.Resume_post_record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Resume_post_recordService extends BaseService<Integer, Resume_post_record> {
	int selectjob(Integer enterpriseId);
	int selectjob1(Integer enterpriseId);
	List<Map> selectBy(String title,Integer enterpriseId,Integer userId,Integer state,Integer start,Integer size);
	int selectCounts(String title,Integer enterpriseId,Integer userId,Integer state);

	int findByResumeId(Integer resumeId);
}
