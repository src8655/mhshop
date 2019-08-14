package com.cafe24.mhmall.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ZipkinAspect {
	
	@Autowired
	private Tracer tracer;
	

    @Around("execution(* com.cafe24.mhmall.repository.impl.*.*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {   
        String methodName = pjp.getSignature().getName();
    	 
    	Span newSpan = tracer.createSpan("DB :: " + methodName);
		tracer.continueSpan(newSpan);
		
        Object result = pjp.proceed();
        
		tracer.close(newSpan);
		
        return result;
    }
}
