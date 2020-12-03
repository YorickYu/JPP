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
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


@Component
@Aspect
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private static final Set<Count<Integer>> SET = new HashSet<>();
    private static final ThreadLocal<Count<Integer>> TL = ThreadLocal.withInitial(() -> {
        Count<Integer> requestCount = new Count<>(0);
        SET.add(requestCount);
        return requestCount;
    });

    private synchronized void saveAdd(Count<Integer> c) {
        SET.add(c);
    }

    @Pointcut("@annotation(com.yy.annotation.Log.START)")
    private void before(){}
    @Pointcut("@annotation(com.yy.annotation.Log.END)")
    private void after(){}
    @Pointcut("@annotation(com.yy.annotation.Log.MIDDLE)")
    private void around(){}

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
        // 累计请求次数
        Count<Integer> count = TL.get();
        count.setT(count.getT()+1);

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

    @Before("before()")
    public void before(JoinPoint joinPoint) {
        structure(joinPoint, LogType.START);
        /* TODO */
    }

    @AfterReturning(value = "after()" ,returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        structure(joinPoint, LogType.END);
        logger.info("RESULT: "+ result.toString());
        /* TODO */
    }

    @AfterThrowing(value = "after()", throwing = "exception")
    public void exce(JoinPoint joinPoint, Exception exception) {
        /* TODO */
    }

    @Around("around()")
    public Object middle(ProceedingJoinPoint joinPoint) {
        Object proceed = null;
        try {
            LocalTime start = LocalTime.now();
            structure(joinPoint, LogType.MIDDLE);
            proceed = joinPoint.proceed(joinPoint.getArgs());
            /* TODO */
            LocalTime end = LocalTime.now();// 终止时间
            Duration duration = Duration.between(start, end);
            logger.info("TIME: "+duration.toMillis()+"ms");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }

    /**
     * 获取标记方法的总请求数量
     * @return total requset count
     */
    public Integer requestStatus() {
        return SET.stream().map(Count::getT).reduce(Integer::sum).orElse(-1);
    }

    /**
     * 持久化计数
     * @param <T>
     */
    public static class Count<T> {
        T t;
        public Count(T t) {
            this.t = t;
        }
        public T getT() {
            return t;
        }
        public void setT(T t) {
            this.t = t;
        }
    }

}
