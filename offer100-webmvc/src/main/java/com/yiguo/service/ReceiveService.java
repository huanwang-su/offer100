package com.yiguo.service;

import com.yiguo.bean.Receive;

public interface ReceiveService extends BaseService<Integer, Receive> {
    int selectByIds(Receive receive);
}