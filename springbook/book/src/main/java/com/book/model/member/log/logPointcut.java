package com.book.model.member.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class logPointcut {
	@Pointcut("execution(* com.book.model.member..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.book.model.member..*Impl.select*(..))")
	public void selectPointcut() {}
}
