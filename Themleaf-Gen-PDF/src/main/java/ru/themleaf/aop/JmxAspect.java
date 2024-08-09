package ru.themleaf.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.themleaf.jmx.LogFileMBean;

@Aspect
@Component
@RequiredArgsConstructor
public class JmxAspect {

    private final LogFileMBean logFileMBean;

    @AfterThrowing(value = "@annotation(ExceptionsHandled)", throwing = "exception")
    public void sendError(Exception exception) {
        logFileMBean.addError(exception.getMessage());
    }

}
