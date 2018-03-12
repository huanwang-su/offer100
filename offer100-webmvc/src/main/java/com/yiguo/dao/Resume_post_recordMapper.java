package com.yiguo.dao;

import com.sun.javafx.collections.MappingChange;
import com.yiguo.bean.Resume_post_record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface Resume_post_recordMapper extends BaseMapper<Integer, Resume_post_record> {
	int selectjob(Integer enterpriseId);
	int selectjob1(Integer enterpriseId);

	int findByResumeId(Integer resumeId);
	List<Map> selectBy(String title,Integer enterpriseId,Integer userId,Integer state);
	int selectCounts(String title,Integer enterpriseId,Integer userId,Integer state);
}