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
import java.util.*;


@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static final Set<Counter<HashMap<String, Integer>>> SET = Collections.synchronizedSet(new HashSet<>());
    private static final ThreadLocal<Counter<HashMap<String, Integer>>> TL = ThreadLocal.withInitial(() -> {
        Counter<HashMap<String, Integer>> counter = new Counter();
        HashMap<String, Integer> hashMap = new HashMap<>();
        counter.t = hashMap;
        saveAdd(counter);
        return counter;
    });

    private static synchronized void saveAdd(Counter counter) {
        SET.add(counter);
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

    private void count(String method) {
        Counter<HashMap<String, Integer>> counter = TL.get();
        HashMap map = counter.t;
        Integer integer = (Integer) map.get(method);
        if (integer == null)
            integer = 0;
        map.put(method, integer+1);
    }

    private void structure(JoinPoint joinPoint, LogType type) {

        StringBuilder PARAMSTRING = new StringBuilder("ARGS: ");
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
                PARAMSTRING.append("arg").append(i+1).append("= (").append(params[i]).append(":").append(args[i]).append(") ");
            }else {
                logger.warn("arg"+ (i+1) + "参数存在异常");
            }
        }
        logger.info(PARAMSTRING.toString());
        logger.info(new String(new char[temp.length()]).replace("\0", "="));

        count(METHODSTRING);
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
    public static void stat() {
        logger.info("===================STATuS===================");
        SET.stream().map(Counter::getT).forEach( stringIntegerHashMap -> {
            Iterator<String> iterator = stringIntegerHashMap.keySet().iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                logger.info(next +":"+stringIntegerHashMap.get(next)+"次");
            }
        });
        logger.info("===================statUs===================");
    }

    /**
     * 持久化计数
     * @param <T>
     */
    public static class Counter<T> {
        T t;
        public T getT() {
            return t;
        }
        public void setT(T t) {
            this.t = t;
        }
    }

}
