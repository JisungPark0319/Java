package com.book.model.member.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class memberLog {
	@AfterThrowing(pointcut="logPointcut.allPointcut()", throwing="exceptObj")
	 public void exceptionLog(JoinPoint jp, Exception exceptObj) {
		 String method = jp.getSignature().getName();
		 System.out.println("[Member Log] ===> " + method + "() 메소드 실행 중 예외 발생!");
		 
		 if(exceptObj instanceof Exception) {
			 System.out.println("[Member Log Exception] ===> 예외발생 : " + exceptObj);
		 }
	 }
	
	@Around("logPointcut.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object obj = pjp.proceed();
		
		stopWatch.stop();
		
		System.out.println("[Member Log] ===> " + method + "() 메소드 수행 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)초");
		return obj;
	}
}
