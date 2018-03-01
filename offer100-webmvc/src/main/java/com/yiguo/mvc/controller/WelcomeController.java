package com.yiguo.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 首页
 *
 * @author wanghuan
 * @date 2018-01-06
 */
@ApiIgnore
@Controller
public class WelcomeController {

	@RequestMapping("/")
	public String login(String id, String name, String createDate, String password) {
		return "forward:/static/index.html";
	}

	@ApiOperation(value = "重定向", hidden = true)
	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String api() {
		return "redirect:/static/swagger/index.html";
	}

}
