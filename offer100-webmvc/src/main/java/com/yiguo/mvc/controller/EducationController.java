package com.yiguo.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.yiguo.service.EducationService;

@Controller
public class EducationController {
	@Autowired
	EducationService educationService;
}
