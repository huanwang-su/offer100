package com.yiguo.mvc.controller;

import java.util.HashMap;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiguo.bean.User;
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
public class LoginController {
	public static Logger logger = LogManager.getLogger(LoginController.class);
	private static @Autowired RoleService roleService;

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object loginPost(HttpServletRequest request, HttpServletResponse response, @RequestBody User account,
			Model model) throws Exception {
		// type 类型用来标注会员类型,0表示普通会员
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);

		UsernamePasswordToken token = new UsernamePasswordToken(account.getUsername(), account.getPassword());

		String rememberme = request.getParameter("rememberme");
		if (StringUtils.isNotBlank(rememberme)) {
			token.setRememberMe(true);
		} else {
			token.setRememberMe(false);
		}

		try {
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			if (subject.isAuthenticated()) {
				map.put("status", 0);
				map.put("username", account.getUsername());
				map.put("msg", "登陆成功");
				Map<String, Object> authMap = new HashMap<>();
				authMap.put("username", account.getUsername());
				authMap.put("permissions", roleService.findPermissions(account.getUsername()));
				authMap.put("roles", roleService.findRoles(account.getUsername()));
				String tokenString = TokenUtil.generatorES256Token(UtilJson.toString(authMap));
				ShiroSessionUtils.setAttribute("token", tokenString);
				return map;
			}
		} catch (UnknownAccountException uae) {
			map.put("msg", "账号不存在!");
		} catch (IncorrectCredentialsException ice) {
			// 密码不正确
			int num = (Integer) ShiroSessionUtils.getAttribute("loginNum");
			token.clear();
			map.put("msg", "用户名或密码错误,你还可以输入" + (5 - num) + "次");
		} catch (ExcessiveAttemptsException eae) {
			// 输入用户名或密码错误次数过多
			ShiroSessionUtils.setAsLogout();
			token.clear();
			map.put("msg", "输入用户名密码或次数过多,账户已被锁定,半小时后解锁");
		} catch (LockedAccountException lae) {
			map.put("msg", "账号被锁定!");
		} catch (Exception e) {
			logger.error("登录失败", e);
			map.put("msg", "未知错误,请联系管理员.");
		}
		return map;
	}

	/**
	 * 退出
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			subject.logout();
		}
		return "redirect:/offer100";
	}

}
