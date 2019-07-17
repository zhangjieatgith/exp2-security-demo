package cn.zhang.jie.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class TimeAspect {

//	@Around("execution(* cn.zhang.jie.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("TimeAspect start");
		Object [] args = pjp.getArgs();
		System.out.println(Arrays.toString(args));
		long beginTime = System.currentTimeMillis();
		Object obj = pjp.proceed();
		System.out.println("AspceTime cost time : " + (System.currentTimeMillis() - beginTime));
		System.out.println("TimeAspect end");
		return obj;
	}
}
