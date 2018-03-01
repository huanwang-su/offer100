package com.yiguo.dao;

import com.yiguo.bean.Major;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MajorMapper extends BaseMapper<Integer, Major> {

}