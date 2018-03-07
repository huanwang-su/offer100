package com.yiguo.service;


import com.yiguo.bean.Configuration;

import java.util.List;

public interface ConfigurationService extends BaseService<Integer, Configuration> {

    //查询所有记录
    List<Configuration> getAll();

    /*Configuration selectByType(String type);*/

    //按类型删除
   /* int deleteByType(String type);*/

    int FindByType(String  type);


}
