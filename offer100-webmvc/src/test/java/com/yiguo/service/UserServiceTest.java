package com.yiguo.service;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.User;
import com.yiguo.mvc.controller.UserController;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by liyue on 2018/3/1.
 */
public class UserServiceTest extends  BaseServiceTest{
    @Autowired
    UserService userService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    private Resume_post_recordService resume_post_recordService;
    @Test

    public void getUser(){
        User user=new User();
        user.setName("张舒雯");
        user.setUsername("小傲娇");
        user.setAge(23);
        user.setPassword("123131212");
        user.setEducation(3);
        user.setEmail("286311613@qq.com");
        user.setMajorId(18344);
        user.setSchoolId(12);
        user.setSex(1);
        user.setPhone("121313121");
       Integer count=  userService.selectByName(user.getName());
       System.out.print(count);
     if(count==0)
         userService.insert(user);
     else
        System.out.print("sasasa");


    }
    @Test
    @Ignore
    public void updateKey(){
      User user=new User();
      user.setId(21);
    user.setAge(22);
      userService.updateByPrimaryKeySelective(user);
    }
    @Test
    @Ignore
    public void deleteKey(){
        userService.deleteByPrimaryKey(21);
    }
    @Test
    public void resumecount(){
        int resumeNum1 = 0;
        float resumeHanderRate = 0;
        int resumeNum = 0;
        // 获取简历数量
        resumeNum = resumeNum + resume_post_recordService.selectjob(2);
        // 获取简历数量，且state为1
        resumeNum1 = resumeNum1 + resume_post_recordService.selectjob1(2);
        // 计算简历处理率

        if (resumeNum != 0)
            resumeHanderRate = (resumeNum - resumeNum1) / resumeNum;
    }

}
