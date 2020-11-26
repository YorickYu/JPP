package com.yy.annotation;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class TestAop {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Pointcut("execution(* com.yy.annotation..*())")
    public void defaultcut(){}

    @Around("defaultcut() && @annotation(com.yy.annotation.MyAnnotation)")
    public Object log(ProceedingJoinPoint pjp) {

        logger.info("---进入切面---");

        Object[] args = pjp.getArgs();
        String methodName = pjp.getSignature().getName();
        Object proceed = null;
        try {
            System.out.println("环绕前置通知"+methodName+ Arrays.asList(args));
            // 利用反射调用目标方法，即method.invoke(obj, args);
            proceed = pjp.proceed(args);
            System.out.println("环绕返回通知"+proceed);
        } catch (Throwable e) {
            System.out.println("环绕异常通知"+e);
            throw new RuntimeException(e);
        } finally {
            System.out.println("环绕后置通知");
        }
        return proceed;
    }

}
