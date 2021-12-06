package com.kca.csg.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution( * kca.csg.controller.*.*(..)) || execution( * kca.csg.service.*.*(..))")
    public Object doAroundControl(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        // TODO @codepark : describe specifically first, after then make them concisely. 
        logger.debug("The object what execute Advice : '" + proceedingJoinPoint.getThis() + "'");
        logger.debug("The object type which execute the Advice : '" + proceedingJoinPoint.getKind() + "'");
        logger.debug("The object throwing this args : '" + proceedingJoinPoint.getArgs() + "'");
        logger.debug("The Target Object is : '" + proceedingJoinPoint.getTarget() + "'");

        String callerPostfix = "";
        String callerName = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        
        if (callerName.contains("Controller")) { callerPostfix = "[Controller] '"; }
        else if (callerName.contains("Service")) { callerPostfix = "[Service] '"; }

        logger.debug(callerPostfix + callerName + "." + proceedingJoinPoint.getSignature().getName() + "()'");
        return proceedingJoinPoint.proceed();
    }
}
