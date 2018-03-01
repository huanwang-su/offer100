package com.yiguo.dao;

import com.yiguo.bean.Collection;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CollectionMapper extends BaseMapper<Integer, Collection> {

}