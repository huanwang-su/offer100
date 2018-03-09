package com.yiguo.service;

import com.yiguo.bean.Education;
import com.yiguo.bean.School;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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
  @Ignore
    public void updateSchool(){
        Education education=new Education();
        education.setId(1);
        education.setSchoolTitle("巴巴爸爸爸爸");
        educationService.updateByPrimaryKeySelective(education);
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
