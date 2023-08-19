package com.example.employee.jpa.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;  
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component; 


@Aspect  
@Component  
public class EmployeeAspect { 
	
	  private final Logger log = LoggerFactory.getLogger(this.getClass()); 
	  @Before("businessService()")
	  public void allBeforeServiceMethodsAdvice(JoinPoint joinPoint){
		 log.info("Before method: {}" ,joinPoint.getSignature());  
	  }
	 
	  @After("businessService()")
	  public void allAfterServiceMethodsAdvice(JoinPoint joinPoint){
		  log.info("After method: {}" ,joinPoint.getSignature());  
	  }
	  
	  @Pointcut("execution(* com.example.employee.jpa.*.*.*(..))&& !execution(* com.example.employee.jpa.filter.*.*(..)) ")
	  public void businessService() {}
	  
	 
} 


/*<dependency> 
    		<groupId>org.springframework.boot</groupId> 
    		<artifactId>spring-boot-starter-aop</artifactId> 
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
		<dependency>
    		<groupId>org.aspectj</groupId>
    		<artifactId>aspectjweaver</artifactId>
    
		</dependency>
		
		*/


























//&& !execution(* com.example.employee.jpa.filter.*.*(..))