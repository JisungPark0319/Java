package com.book.model.articlecontent.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class logPointcut {
	@Pointcut("execution(* com.book.model.articlecontent..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.book.model.articlecontent..*Impl.select*(..))")
	public void selectPointcut() {}
}
