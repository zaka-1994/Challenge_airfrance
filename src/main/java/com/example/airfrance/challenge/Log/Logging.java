package com.example.airfrance.challenge.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.*;

/**
 * Class logging to manage log with AOP
 * @author zhaimi
 * @Date 05/12/2021
 */
@Aspect
@Component
public class Logging {
	
	Logger log = LoggerFactory.getLogger(Logging.class);
	
	
	/**
	 *  Log all the files in packages that starts with
	 *   com.example.airfrance.challenge
	 */
	@Pointcut(value = "execution(* com.example.airfrance.challenge.*.*.*(..))")
	public void myPointcut() {
		
	}
	
	/**
	 * This method manage log
	 * @param pjp
	 * @return the result of proceeding
	 * @throws Throwable
	 */
	@Around(value = "myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		Object[] array = pjp.getArgs();
		// log when the method is called
		log.info("method invoked "+ className + " : " + methodName + "()");
		Object object = pjp.proceed();
		
		// log after getting the result
		log.info(className + " : " + methodName + "()" + "Reponse : "+ mapper.writeValueAsString(object));
		return object;
	}
}
