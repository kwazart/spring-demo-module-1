package com.polozov.springDemo.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingServiceAspect {

    @Pointcut("@annotation(com.polozov.springDemo.annotation.MethodLogger)")
    private void methodLoggerAnnotated() {}

    @Before("methodLoggerAnnotated()")
    public void logServiceBefore(JoinPoint point) {
        log.info("Called method: " + point.getSignature().getName());
    }
}
