package com.yiguo.aop;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;
import com.yiguo.bean.User;

/**
 * 示例
 *
 * @author wanghuan
 * @date 2018-01-12
 */
@Component // 加入到IoC容器
@Aspect // 指定当前类为切面类
public class ExampleAop {

	// 指定切入点表达式，拦截那些方法，即为那些类生成代理对象
	// @Pointcut("execution(* com.bie.aop.UserDao.save(..))") ..代表所有参数
	// @Pointcut("execution(* com.bie.aop.UserDao.*())") 指定所有的方法
	// @Pointcut("execution(* com.bie.aop.UserDao.save())") 指定save方法

	@Pointcut("execution(* com.yiguo.service.UserService.add(..))")
	public void pointCut() {
	}

	@Before(value = "pointCut() && args(user)")
	public void begin(User user) {
		System.out.println("before " + user);
	}

	@After(value = "pointCut()")
	public void close(JoinPoint point) {
		point.getArgs();
		System.out.println("after");
	}

	@AfterThrowing(value = "pointCut()", throwing = "t")
	public void AfterThrowing(Throwable t) {
		System.out.println("method AfterThrowing " + t);
	}

	@AfterReturning(value = "pointCut()", returning = "obj")
	public void AfterReturning(Object obj) {
		System.out.println("method AfterReturning " + obj);
	}

	// @Around(value = "execution(*
	// com.xxx.webmvc.templete.service.UserService.query(..)) && args(user)")
	public Object Around(ProceedingJoinPoint pjp, User user) {

		System.out.println("method Around start");
		Object object = null;
		try {
			object = pjp.proceed();
		} catch (Throwable e) {
			System.out.println("method exception");
		}
		System.out.println("method Around end and return " + object);
		return object;
	}

}
