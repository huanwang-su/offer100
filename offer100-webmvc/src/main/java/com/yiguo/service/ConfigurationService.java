package com.yiguo.service;


import com.yiguo.bean.Configuration;

import java.util.List;

public interface ConfigurationService extends BaseService<Integer, Configuration> {

    //查询所有记录
    List<Configuration> getAll();

    //按类型查询记录
    Configuration findByType(String type);

    //按类型删除
    int deleteByType(String type);

    int FindByType(String  type);


}
