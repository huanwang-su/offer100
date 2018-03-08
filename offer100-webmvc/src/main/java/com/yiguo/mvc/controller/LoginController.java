package com.yiguo.mvc.controller;

import java.util.HashMap;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.User;
import com.yiguo.service.EnterpriseService;
import com.yiguo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.yiguo.permission.shiro.ShiroSessionUtils;
import com.yiguo.permission.token.TokenUtil;
import com.yiguo.service.RoleService;
import com.yiguo.utils.UtilJson;

/**
 * 登陆控制器
 *
 * @author wanghuan
 * @date 2018-01-06
 */
@Controller
@Api(value = "API - LoginController", description = "登录认证")
@RequestMapping(value="/login")
public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	EnterpriseService enterpriseService;
	@ApiOperation(value = "用户登录",notes = "验证用户登录")
	@ResponseBody
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public Object loginUser(@PathVariable String username, @PathVariable String password,@PathVariable String type) {
		// 处理"/users/"的POST请求，用来创建User
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		String f="登录成功";
		if(type.equals("user")){
		User user= userService.findByUsername(username);
		if(user!=null)
		{
			if(!user.getPassword().equals(password))
				f="用户名或者密码不对";
			else if(user.getState()==0)
				f="此用户已经被封，不可用";
		}
		else
			f="此用户不存在，请先注册";
		if(f.equals("登录成功"))
			return user;
		}
		else if(type.equals("enterprise")) {
			Enterprise enterprise = enterpriseService.findByUsername(username);
			if (enterprise != null) {
				if (!enterprise.getUserPassword().equals(password))
					f = "用户名或者密码不对";

			}
			else
				f="此用户不存在，请先注册";
			if(f.equals("登录成功"))
				return  enterprise;
		}
	return f;
	}

	@ApiOperation(value = "用户注销",notes = "用户注销账户" )
	@ResponseBody
	@RequestMapping(value = "/user/exit", method = RequestMethod.POST)
	public String exitUser(@PathVariable String username) {
		// 处理"/users/"的POST请求，用来创建User
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
	String f="注销";
			User user= userService.findByUsername(username);
		    userService.deleteByPrimaryKey(user.getId());
		    f="注销成功";
		return f;
	}
}
