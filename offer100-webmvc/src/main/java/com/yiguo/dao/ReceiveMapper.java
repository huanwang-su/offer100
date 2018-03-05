package com.yiguo.dao;

import com.yiguo.bean.Question;
import com.yiguo.bean.Receive;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReceiveMapper extends BaseMapper<Integer, Receive> {
    int selectByIds(Receive receive);
}