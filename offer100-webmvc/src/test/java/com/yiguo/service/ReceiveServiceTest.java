package com.yiguo.service;

import com.yiguo.bean.Receive;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liyue on 2018/3/2.
 */
public class ReceiveServiceTest extends BaseServiceTest {

@Autowired
ReceiveService receiveService;
@Test
@Ignore
    public void getReceive(){

    Receive receive=new Receive();
    receive.setJobId(1);
    receive.setUserId(1);
 int count=receiveService.selectByIds(receive);
 if(count==0)
receiveService.insert(receive);
 else
     System.out.print("+++++++++++++++++++++++++++++++++++=");
}
@Test
@Ignore
    public void selectReceive(){
        System.out.print(receiveService.selectByPrimaryKey(2));
}
@Test
@Ignore
    public void updateReceive(){
    Receive receive=new Receive();
    receive.setJobId(3);
    receive.setId(2);
    receiveService.updateByPrimaryKeySelective(receive);
}
@Test
    public void deleteReceive(){
        receiveService.deleteByPrimaryKey(2);
}
}
