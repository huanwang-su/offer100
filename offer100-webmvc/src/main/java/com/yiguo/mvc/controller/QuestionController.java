package com.yiguo.mvc.controller;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Page;
import com.yiguo.bean.Question;
import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "API - QuestionController", description = "求职问答")
@RequestMapping("/question")
public class QuestionController {
	@Autowired
    QuestionService questionService;
    @ApiOperation(value = "创建问答",notes = "根据Question对象创建Question")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postQuestion(@RequestBody Question question) {
        // 处理"/Zones/"的POST请求，用来创建Zone
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        String f="提问成功";

            questionService.insert(question);


        return f;
    }
    @ApiOperation(value = "提取问答",notes = "提取问答列表")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public PageInfo<Question> getQuestion(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        // 处理"/Zones/"的POST请求，用来创建Zone
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        Question question=new Question();
        PageInfo<Question> pageinfo=new PageInfo<Question>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

      pageinfo.setRows(questionService.select(question,page));

pageinfo.setTotal(questionService.selectCount(question));
        return pageinfo;
    }
}
