package com.yiguo.dao;

import com.yiguo.bean.Resume_post_record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface Resume_post_recordMapper extends BaseMapper<Integer, Resume_post_record> {
	int selectjob(Integer jobId);
	int selectjob1(Integer jobId);
}