package com.yiguo.service;

import com.yiguo.bean.Page;
import com.yiguo.bean.Question;
import com.yiguo.bean.User;
import com.yiguo.offer100.common.page.PageInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by liyue on 2018/3/1.
 */
public class QuestionServiceTest extends  BaseServiceTest{
  @Autowired
    QuestionService questionService;
  @Test
  @Ignore
  public void postQuestion() {
      // 处理"/Zones/"的POST请求，用来创建Zone
      // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
      String f="提问成功";
      Question   question=new Question();
question.setContext("dasdasd");
question.setTitle("nihao");
question.setUserId(1);
question.setTime(new Date());
      questionService.insert(question);



  }
  @Test
  @Ignore
    public void getQuestion() {
        // 处理"/Zones/"的POST请求，用来创建Zone
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        Question question=new Question();
        Page page=new Page();
        List<Question> questions =questionService.select(question,page);


        for(int i=0;i<questions.size();i++)
            System.out.println(questions.get(i));
    }
@Test
public void getQuestion1() {
    // 处理"/Zones/"的POST请求，用来创建Zone
    // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
    Question question=new Question();
    PageInfo<Question> pageinfo=new PageInfo<Question>();
    pageinfo.setPageNum(30);
    pageinfo.setPageSize(1);
    Page page= new Page();
    page.setPageNumber(1);
    page.setPageSize(30);
    pageinfo.setRows(questionService.select(question,page));

    pageinfo.setTotal(questionService.selectCount(question));
  System.out.println(pageinfo.getTotal());
  System.out.println(pageinfo.getRows());
}
}
