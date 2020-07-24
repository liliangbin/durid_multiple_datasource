package com.example.oracle.aop.innovation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class Userclas {
    private static final Logger LOGGER = LoggerFactory.getLogger(Userclas.class);

    @Pointcut("@annotation(com.example.oracle.aop.innovation.LiliangbinCl)")
    public void executeService() {

    }

    @Before("executeService()")
    public void doBeforeAdvice(JoinPoint joinPoint) throws Exception {
        joinPoint.getTarget();
        LOGGER.info("Before [{}]", joinPoint.getSignature().getName());
    }

    @Around("executeService()")
    public void doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("Around1 [{}]", joinPoint.getSignature().getName());
        joinPoint.proceed();
        LOGGER.info("Around2 [{}]", joinPoint.getSignature().getName());
    }

    @After("executeService()")
    public void doAfterAdvice(JoinPoint joinPoint) throws Exception {
        LOGGER.info("After [{}]", joinPoint.getSignature().getName());
    }

    @AfterReturning("executeService()")
    public void doAfterReturningAdvice(JoinPoint joinPoint) throws Exception {
        LOGGER.info("AfterReturning [{}]", joinPoint.getSignature().getName());
    }
}
