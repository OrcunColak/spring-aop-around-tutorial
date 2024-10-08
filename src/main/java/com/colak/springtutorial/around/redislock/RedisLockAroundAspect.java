package com.colak.springtutorial.around.redislock;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RedisLockAroundAspect {

    /**
     * The @Around aspect for @RedisLock
     */
    @Around("@annotation(redisLock)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint, RedisLock redisLock) throws Throwable {
        // Get the values from ProceedingJoinPoint  example
        try {
            log.info("RedisLock Around aspect started");
            Object[] args = joinPoint.getArgs();
            Object targetObject = joinPoint.getThis();
            log.info("TargetObject : {} args : {}", targetObject, args);
            log.info("RedisLock key: {}", redisLock.key());
            return joinPoint.proceed();
        } finally {
            log.info("RedisLock Around aspect finished");
        }
    }
}