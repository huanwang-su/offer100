package com.yiguo.service;

import com.yiguo.bean.User;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

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
@Ignore
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
@Test
public void loginUser() {
    // 处理"/users/"的POST请求，用来创建User
    // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
    String username="wanghuan";
    String password="wnghuan";
    String f="登录成功";
    User user= userService.findByUsername(username);
    if(user!=null)
    {
        if(!user.getPassword().equals(password))
            f="用户名或者密码不对";
        else if(user.getState()==0)
            f="此用户已经被封，不可用";
    }
    else
        f="此用户不存在，请先注册";
    //System.out.println(f);
   System.out.println(f);
}
}
