package com.yiguo.service;

import com.yiguo.bean.Enterprise;

public interface EnterpriseService extends BaseService<Integer, Enterprise> {
   Enterprise selectByIds(String userName,String userPassword);
   Enterprise findByUsername(String username);
}
