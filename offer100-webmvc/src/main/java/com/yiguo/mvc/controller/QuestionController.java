package com.yiguo.mvc.controller;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Page;
import com.yiguo.bean.Question;
import com.yiguo.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(value = "API - QuestionController", description = "求职问答")
@RequestMapping("/question")
public class QuestionController {
	@Autowired
    QuestionService questionService;
    @ApiOperation(value = "创建问答",notes = "根据Question对象创建Question")
    @ResponseBody
    @RequestMapping(value = "/admin/question", method = RequestMethod.POST)
    public String postQuestion(@RequestBody Question question) {
        // 处理"/Zones/"的POST请求，用来创建Zone
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        String f="提问成功";

            questionService.insert(question);


        return f;
    }
    @ApiOperation(value = "提取问答",notes = "提取问答列表")
    @ResponseBody
    @RequestMapping(value = "/getQuestion", method = RequestMethod.POST)
    public List<Question> getQuestion() {
        // 处理"/Zones/"的POST请求，用来创建Zone
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        Question question=new Question();
        Page page=new Page();
        List<Question> questions =questionService.select(question,page);


        return questions;
    }
}
