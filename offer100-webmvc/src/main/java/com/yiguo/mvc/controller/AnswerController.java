package com.yiguo.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yiguo.service.AnswerService;

@Controller
public class AnswerController {


	@Autowired
	AnswerService anserservice;
}
