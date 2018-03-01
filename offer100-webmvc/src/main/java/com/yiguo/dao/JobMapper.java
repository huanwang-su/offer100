package com.yiguo.dao;

import com.yiguo.bean.Job;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface JobMapper extends BaseMapper<Integer, Job> {
	List<Job> querySearch(Integer enterpriseId);

}