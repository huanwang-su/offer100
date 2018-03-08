package com.yiguo.dao;


import com.yiguo.bean.Configuration;

import java.util.Date;
import java.util.List;

public interface ConfigurationMapper extends BaseMapper<Integer, Configuration>{

    //查询
     List<Configuration> query();

     //根据type查询
     Configuration selectByType(String type);

    //根据type删除
    int deleteByType(String type);

     //根据type查询是否存在这条记录
     int findByType(String type);

    int findById(Integer id);






}
