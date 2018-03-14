package com.yiguo.mvc.controller;

import java.util.HashMap;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.Authenticator;
import com.yiguo.bean.Enterprise;
import com.yiguo.bean.User;
import com.yiguo.service.EnterpriseService;
import com.yiguo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import ch.qos.logback.classic.Logger;
import org.omg.PortableInterceptor.SUCCESSFUL;
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

import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;
import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;

/**
 * 登陆控制器
 *
 * @author wanghuan
 * @date 2018-01-06
 */
@Controller
@Api(value = "用户登录接口")
@RequestMapping(value="/login")
public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	EnterpriseService enterpriseService;
	@ApiOperation(value = "用户登录",notes = "验证用户登录")
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> loginUser(@RequestBody Map<String, Object> loginInfo) {
		// 处理"/users/"的POST请求，用来创建User
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		Map<String, Object> m = new HashMap<String, Object>();
		String f="登录成功";
		if(loginInfo.get("type").toString().equals("user")){
			User user= userService.findByUsername(loginInfo.get("username").toString());
			if(user!=null)
			{
				if(!user.getPassword().equals(loginInfo.get("password").toString())) {
					f = "用户名或者密码不对";
					m.put("msg",f);
				}
				else if(user.getState()==0) {
					f = "此用户已经被封，不可用";
					m.put("msg",f);
				}
			}
			else {
				f = "此用户不存在，请先注册";

				m.put("msg",f);
			}

			if(f.equals("登录成功"))
			{
				m.put("username", user.getUsername());
				m.put("id", user.getId());
				m.put("type", loginInfo.get("type").toString());
				m.put("msg",f);
			}
			return m;
		}
		else if(loginInfo.get("type").toString().equals("enterprise")) {
			Enterprise enterprise = enterpriseService.findByUsername(loginInfo.get("username").toString());
			if (enterprise != null) {
				if (!enterprise.getUserPassword().equals(loginInfo.get("password").toString())) {

					f = "用户名或者密码不对";

					m.put("msg",f);
				}
				else if(enterprise.getState()==0) {
					f = "此用户已经被封，不可用";

					m.put("msg",f);
				}
			}
			else {
				f = "此用户不存在，请先注册";

				m.put("msg",f);
			}
			if(f.equals("登录成功"))
			{
				m.put("username", enterprise.getUserName());
				m.put("id", enterprise.getId());
				m.put("type", loginInfo.get("type").toString());
				m.put("msg",f);
			}
			return m;
		}
		else{
			m.put("msg","没有此用户权限");
		}
		return m;
	}

	@ApiOperation(value = "用户注销",notes = "用户注销账户" )
	@ResponseBody
	@RequestMapping(value = "/exit/{username}", method = RequestMethod.POST)
	public String exitUser(@PathVariable String username) {
		// 处理"/users/"的POST请求，用来创建User
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		String f= SUCCESS;
		User user= userService.findByUsername(username);
		userService.deleteByPrimaryKey(user.getId());
		if(userService.selectByPrimaryKey(user.getId())!=null) {
			f = FAILURE;
		}
		return f;
	}

}
