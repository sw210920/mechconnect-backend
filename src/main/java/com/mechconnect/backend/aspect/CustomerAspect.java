package com.mechconnect.backend.aspect;

/**
 * CustomerAspect
 *
 * Part of the MechConnect backend application.
 * Responsible for handling aspect related logic.
 */


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomerAspect {
	
	@Pointcut("execution(* com.projectpractice.projectpractice.Controller.CustomerController.*(..))")

	public void allMethodsofController() {
		
	}
	@Before("allMethodsofController()")
	public void runBefore (JoinPoint JoinPOint) {
		System.out.println("From Aspect :");
		System.out.println("User Controller started");
	}
	
	@After("allMethodsofController()")
	public void runAfter (JoinPoint JoinPOint) {
		System.out.println("From Aspect After:");
		System.out.println("User Controller end");
		}

}
