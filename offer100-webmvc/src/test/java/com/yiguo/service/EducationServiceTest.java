package com.yiguo.service;

import com.yiguo.bean.Education;
import com.yiguo.bean.School;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;
import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;

/**
 * Created by liyue on 2018/3/2.
 */
public class EducationServiceTest extends BaseServiceTest {

     @Autowired
   EducationService educationService;

    @Test

     public void getSchool(){
       Education education=new Education();
       education.setSchoolTitle("sasadasdsad");
       education.setUserId(1);
       education.setUserName("张舒雯");
       education.setStartTime(new Date());
       education.setEndTime(new Date());
      int count=  educationService.selectByIds(education.getUserId());
         if(count==0)
          educationService.insert(education);
      else
          System.out.print("++++++++++++++++++++++++++++++");

     }
    @Test

    public void updateSchool(){
        Education education=new Education();
        education.setId(4);
        education.setSchoolTitle("巴巴爸爸");
        if (educationService.findById(education.getId()) > 0) {
            int num = educationService.updateByPrimaryKeySelective(education);
            if (num > 0) {
               System.out.print("+++++++++++++++++++++++++");
            } else
                System.out.print("_____________________________");
        }

    }
    @Test
  @Ignore
    public void querySchool(){
System.out.print(        educationService.selectByPrimaryKey(1));
    }
    @Test
    @Ignore
    public void deleteSchool(){
        educationService.deleteByPrimaryKey(1);
    }
}
