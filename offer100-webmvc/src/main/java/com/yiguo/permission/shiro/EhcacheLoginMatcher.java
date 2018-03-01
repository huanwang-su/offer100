package com.yiguo.permission.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;

public class EhcacheLoginMatcher extends HashedCredentialsMatcher {

	private final Cache<String, AtomicInteger> passwordCache; // 原子

	public EhcacheLoginMatcher(CacheManager cacheManager) {
		passwordCache = cacheManager.getCache("passwordCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// +1
		AtomicInteger retryCount = passwordCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordCache.put(username, retryCount);
		}
		int m = retryCount.incrementAndGet();
		ShiroSessionUtils.setAttribute("loginNum", m);
		if (m > 5) {
			// count > 5 throw 5次登陆异常
			throw new ExcessiveAttemptsException();
		}

		String password = String.valueOf((char[]) token.getCredentials());
		String credentials = new Md5Hash(password).toString();
		boolean matches = credentials.equals(info.getCredentials().toString());

		if (matches) {
			// 去掉错误记录
			passwordCache.remove(username);
		} else {
			passwordCache.put(username, retryCount);
		}
		return matches;
	}

	public static void main(String[] args) {
		System.out.println(new Md5Hash("222").toString());
	}
}
