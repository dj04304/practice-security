package com.security.practicesecurity.handler.aop;

import java.security.Signature;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAop {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Pointcut("@annotation(com.security.practicesecurity.handler.aop.annotation.Log)")
	private void enableLog() {}
	
	@Around("enableLog()")
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable{
		Map<String, Object> params = getParams(joinPoint);
		
		Object result = joinPoint.proceed();
		
		return result;
		
	}
	
	private Map<String, Object> getParams(ProceedingJoinPoint joinPoint) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
		String[] argsName = codeSignature.getParameterNames();
		Object[] args = joinPoint.getArgs();
		
		for(int i = 0; i < argsName.length; i++) {
			params.put(argsName[i], args[i]);
		}
		
		return params;
	}

	

}


