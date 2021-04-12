package com.book.model.article.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class logPointcut {
	@Pointcut("execution(* com.book.model.article..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.book.model.article..*Impl.select*(..))")
	public void selectPointcut() {}
}
