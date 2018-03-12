package com.yiguo.service;

import com.yiguo.bean.Page;
import com.yiguo.bean.School;
import com.yiguo.offer100.common.page.PageInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyue on 2018/3/2.
 */
public class SchoolServiceTest extends BaseServiceTest {

     @Autowired
   SchoolService schoolService;

    @Test
    @Ignore
     public void getSchool(){
        School school=new School();
        school.setName("南方daxue");
        school.setType("986");
        school.setZoneId(1);
        school.setProperties("asasa");


      int count=  schoolService.selectByName(school.getName());
      if(count==0)
          schoolService.insert(school);
      else
          System.out.print("++++++++++++++++++++++++++++++");

     }
    @Test
    @Ignore
    public void updateSchool(){
        School school=new School();
        school.setId(1462);
        school.setProperties("你好啊");
        schoolService.updateByPrimaryKeySelective(school);
    }
    @Test
    @Ignore
    public void querySchool(){
     System.out.print(   schoolService.selectByPrimaryKey(1462));
    }
    @Test
    public void selectSchool() {
        // 处理"/Schools/{id}"的DELETE请求，用来删除School
        List<String> schools=new ArrayList<String>();

            schools=schoolService.findByZoneId(2);

         System.out.println(schools);
    }
}
