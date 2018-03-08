package com.yiguo.permission.shiro;

import com.yiguo.bean.User;
import com.yiguo.service.RoleService;
import com.yiguo.service.UserService;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;


/**
 * 用户认证
 */
public class ShiroAccountRealm extends AuthorizingRealm {
	public Logger logger = (Logger) LoggerFactory.getLogger(getClass());
    @Autowired
	private UserService userService;
    @Autowired
	private RoleService roleService;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		// (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			doClearCache(principalCollection);
			SecurityUtils.getSubject().logout();
			return null;
		}
		String username = (String) principalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User account = new User();
		account.setUsername(username);
		account = userService.query(account).get(0);
		// 获取权限信息
		if (null != account) {
			Set<String> permissions = roleService.findPermissions(username);
			Set<String> roles = roleService.findRoles(username);
			authorizationInfo.addStringPermissions(permissions);
			authorizationInfo.addRoles(roles);
			return authorizationInfo;
		} else {
			throw new AuthorizationException();
		}
	}

	// 认证，基于用户名密码
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		// 根据用户名查找用户
		User user = new User();
		user.setUsername(username);
		List<User> account = userService.query(user);
		if (null != account && account.size() > 0) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(username, account.get(0).getPassword(),
					this.getName());
			ShiroSessionUtils.setAsLogin(account.get(0));
			/**
			 * 关闭浏览器，再打开后，虽然授权缓存了，但是认证是必须的，在认证成功后，清除之前的缓存。
			 */
			clearCache(authcInfo.getPrincipals());

			return authcInfo;
		} else {
			// 认证没有通过
			throw new UnknownAccountException();// 没帐号
		}
	}

}
