package com.yy.annotation;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.Arrays;


@Component
@Aspect
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.yy.annotation.Log.START)")
    private void startcut(){}
    @Pointcut("@annotation(com.yy.annotation.Log.END)")
    private void aftercut(){}
    @Pointcut("@annotation(com.yy.annotation.Log.MIDDLE)")
    private void aroundcut(){}

    private String getDescription(Object target, String method, LogType type) {
        Class<?> aClass = target.getClass();
        // 获取方法上的注解
        for (Method m: aClass.getDeclaredMethods()) {
            if (!m.getName().equals(method))
                continue;
            return getAnnotationDescription(m, type);
        }
        return "";
    }

    private String getAnnotationDescription(Method m, LogType type) {
        String description = "DESC: ";
        switch (type) {
            case START: description += m.getDeclaredAnnotation(Log.START.class).description();break;
            case END: description += m.getDeclaredAnnotation(Log.END.class).description();break;
            case MIDDLE: description += m.getAnnotation(Log.MIDDLE.class).description();break;
        }
        return description;
    }

    private void structure(JoinPoint joinPoint, LogType type) {
        String PARAMSTRING = "ARGS: ";
        String METHODSTRING;
        // 获取参数值
        Object[] args = joinPoint.getArgs();
        // 获取参数名称
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature; //获取参数名
        String[] params = methodSignature.getParameterNames();

        METHODSTRING = signature.getDeclaringTypeName() + "::" + signature.getName();

        String temp = "";
        logger.info( temp = (type+"=======" + METHODSTRING +"============"));

        String description = getDescription(joinPoint.getTarget(), signature.getName(), type);
        logger.info(description);

        for (int i = 0; i < params.length; i++) {
            if (i < args.length && args[i] != null) {
                PARAMSTRING += "arg"+ (i+1) +"=" + "(" +params[i] + ": " + args[i].toString()+") ";
            }else {
                logger.warn("arg"+ (i+1) + "参数存在异常");
            }
        }
        logger.info(PARAMSTRING);
        logger.info(new String(new char[temp.length()]).replace("\0", "="));
    }

    @Before("startcut()")
    public void before(JoinPoint joinPoint) {
        structure(joinPoint, LogType.START);
        // 埋点\布隆 ...
    }

    @AfterReturning(value = "aftercut()" ,returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        structure(joinPoint, LogType.END);
        logger.info("RESULT: "+ result.toString());
        // ...
    }

    @AfterThrowing(value = "aftercut()", throwing = "exception")
    public void exce(JoinPoint joinPoint, Exception exception) {

    }

    @Around("aroundcut()")
    public Object middle(ProceedingJoinPoint joinPoint) {
        Object proceed = null;
        try {
            long l = System.currentTimeMillis();
            structure(joinPoint, LogType.MIDDLE);
            proceed = joinPoint.proceed(joinPoint.getArgs());
            String last = String.valueOf(System.currentTimeMillis()-l);
            logger.info("TIME: "+last);
            // ...
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }

}
