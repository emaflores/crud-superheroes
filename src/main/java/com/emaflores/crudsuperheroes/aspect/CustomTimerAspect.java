package com.emaflores.crudsuperheroes.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomTimerAspect {
    private static final Logger logger = LoggerFactory.getLogger(CustomTimerAspect.class);

    @Around("@annotation(com.emaflores.crudsuperheroes.annotation.CustomTimer)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        logger.info("Method '{}' executed in {} ms", joinPoint.getSignature().toShortString(), duration);
        return result;
    }
}

