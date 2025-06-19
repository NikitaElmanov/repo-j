package ru.themleaf.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.themleaf.jmx.LogFileMBean;

@Aspect
@Component
@RequiredArgsConstructor
public class JmxAspect {

    private final LogFileMBean logFileMBean;

//    @AfterThrowing(value = "@annotation(ExceptionsHandled)", throwing = "exception")
//    public void sendError(Exception exception) {
//        logFileMBean.addError(exception.getMessage());
//    }

    @After(value = "execution(* ch.qos.logback.classic.Logger.error(..))")
    public void sendError(JoinPoint joinPoint) {
        if (joinPoint.getArgs()[0] instanceof String errorMessage) {
            logFileMBean.addError(errorMessage);
        }
    }

    @Around("execution(* *..Logger.error(..))")
    public void formatMessageLog(ProceedingJoinPoint joinPoint) throws Throwable {
//        logFileMBean.addError(format);
        final Object[] args = joinPoint.getArgs();
        System.out.println(args[0]);
        joinPoint.proceed(args);
    }

    @Around("execution(* *.error(..))")
    public void formatMessageLogasas(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed(joinPoint.getArgs());
    }

}
