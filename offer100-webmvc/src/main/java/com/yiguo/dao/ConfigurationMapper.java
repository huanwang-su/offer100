package com.yiguo.dao;


import com.yiguo.bean.Configuration;

import java.util.Date;
import java.util.List;

public interface ConfigurationMapper extends BaseMapper<Integer, Configuration>{

    //查询
     List<Configuration> query();
    //根据主键查询
     Configuration selectByPrimaryKey(Integer id);
     //根据key查询
     Configuration selectByKey(String key);
     //根据主键删除
     int deleteByPrimaryKey(Integer id);
    //根据key删除
     int deleteByKey(String key);
     // 插入数据
     int insert(Configuration configuration);
    //根据主键插入数据
     int insertSelective(Configuration configuration);
    //根据主键更新
     int updateByPrimaryKeySelective(Configuration configuration);
    //全部更新
    // int updateByPrimaryKey();
    //查询数量
     int selectCount();




}
