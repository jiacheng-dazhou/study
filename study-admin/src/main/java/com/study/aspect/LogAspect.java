package com.study.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.study.annotation.Log)")
    public void logPointCut(){

    }

    @Around("logPointCut()")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable{
        String name = joinPoint.getSignature().getName();
        log.info(name+"方法开始执行...");
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info(name+"方法执行结束...");
        log.info(name+"方法执行时间："+((endTime-startTime)/1000)+"秒");
    }
}
