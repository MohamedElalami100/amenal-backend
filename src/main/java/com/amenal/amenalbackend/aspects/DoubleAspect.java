package com.amenal.amenalbackend.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Aspect
@Component
public class DoubleAspect {


    @Pointcut("execution(public Double com.amenal.amenalbackend.budget.core.domain..get*())")
    public void doubleGetterPointcut() {
    }

    @Around("doubleGetterPointcut()")
    public Object formatDouble(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        if (result instanceof Double) {
            Double doubleValue = (Double) result;
            Double formattedValue = BigDecimal.valueOf(doubleValue)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
            return formattedValue;
        }
        return result;
    }
}