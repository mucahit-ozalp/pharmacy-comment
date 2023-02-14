package com.app.pharmacy.aop;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Aspect
@Component
public class PharmacyAspect {
  private static final Logger logger = LoggerFactory.getLogger(
      PharmacyAspect.class);

  @Before("execution(* com.app.pharmacy.service.PharmacyService.getOpenPharmacy(..))")
  public void getAccountOperationInfo(JoinPoint joinPoint) {

    // Method Information
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();

    logger.info("***** AOP open *****");

    logger.info("full method description: " + signature.getMethod());
    logger.info("method name: " + signature.getMethod().getName());
    logger.info("declaring type: " + signature.getDeclaringType());

    // Method args
    System.out.println("Method args names:");
    Arrays.stream(signature.getParameterNames())
        .forEach(s -> logger.info("arg name: " + s));

    System.out.println("Method args types:");
    Arrays.stream(signature.getParameterTypes())
        .forEach(s -> logger.info("arg type: " + s));

    System.out.println("Method args values:");
    Arrays.stream(joinPoint.getArgs())
        .forEach(o -> logger.info("arg value: " + o.toString()));

    // Additional Information
    logger.info("returning type: " + signature.getReturnType());
    logger.info("method modifier: " + Modifier.toString(signature.getModifiers()));
    Arrays.stream(signature.getExceptionTypes())
        .forEach(aClass -> logger.info("exception type: " + aClass));

    //TODO will write proceed method
//    Object returnedByMethod = joinPoint.proceed();

    logger.info("***** AOP close *****");

  }
}
