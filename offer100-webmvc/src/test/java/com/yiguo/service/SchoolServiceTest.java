package com.yiguo.service;

import com.yiguo.bean.Page;
import com.yiguo.bean.School;
import com.yiguo.offer100.common.page.PageInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
        School school =new School();
        school.setZoneId(2);
        PageInfo<School> pageinfo=new PageInfo<School>();
        pageinfo.setPageNum(1);
        pageinfo.setPageSize(30);
        Page page= new Page();
        page.setPageNumber(1);
        page.setPageSize(30);
        pageinfo.setRows( schoolService.select(school,page));
        int count=schoolService.selectCount(school);
        pageinfo.setTotal(count);

 System.out.println(count+"+++++++++++++++++++++++++++++++++++++++++++++++++=");
 System.out.println(pageinfo.getRows());
    }
}
