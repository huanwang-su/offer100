package com.yiguo.dao;

import com.yiguo.bean.Bulletin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BulletinMapper extends BaseMapper<Integer, Bulletin> {

    List<Bulletin> query( );

    int findById(Integer id);

}