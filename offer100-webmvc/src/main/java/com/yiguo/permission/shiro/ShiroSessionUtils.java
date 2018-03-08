package com.yiguo.permission.shiro;

import com.yiguo.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * shiro Session工具类
 *
 * @author wanghuan
 * @date 2018-01-09
 */
public class ShiroSessionUtils {

	public static final String LOGIN_ATTRIVUTE_NAME = "account";

	public static void setAttribute(Object key, Object value) {
		Subject account = SecurityUtils.getSubject();
		if (null != account) {
			Session session = account.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

	public static Object getAttribute(Object key) {
		Subject account = SecurityUtils.getSubject();
		if (null != account) {
			Session session = account.getSession();
			if (null != session) {
				return session.getAttribute(key);
			}
		}
		return null;
	}

	public static Object removeAttribute(Object key) {
		Subject account = SecurityUtils.getSubject();
		if (null != account) {
			Session session = account.getSession();
			if (null != session) {
				return session.removeAttribute(key);
			}
		}
		return null;
	}

	public boolean hasLogin() {
		return getAttribute(LOGIN_ATTRIVUTE_NAME) != null ? true : false;
	}

	public static void setAsLogin(User account) {
		setAttribute(LOGIN_ATTRIVUTE_NAME, account);
	}

	public static void setAsLogout() {
		removeAttribute(LOGIN_ATTRIVUTE_NAME);
	}

	public static User getLoginAccount() {
		return (User) getAttribute(LOGIN_ATTRIVUTE_NAME);
	}

}
