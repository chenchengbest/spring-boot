package com.ct.common.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ct.common.annotation.Log;

/**
 * The type Log aspect.
 *
 * @author chen.cheng
 */
@Aspect
@Component("logAspect")
public class LogAspect {
    /**
     * The constant LINE_SEPARATOR.
     *
     * @author chen.cheng
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * The constant log.
     *
     * @author chen.cheng
     */
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * Log point cut.
     *
     * @author chen.cheng
     */
    @Pointcut("@annotation(com.ct.common.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * Do before.
     *
     * @param joinPoint the join point
     * @author chen.cheng
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        log.debug(">>>>>>>>>>>>>>>>>>> start <<<<<<<<<<<<<<<<<<<<<<<<" + LINE_SEPARATOR);
        handleLog(joinPoint, null);
    }

    /**
     * Do around object.
     *
     * @param proceedingJoinPoint the proceeding join point
     * @return the object
     * @throws Throwable the throwable
     * @author chen.cheng
     */
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String aspectMethodLogDescPJ = getAspectMethodLogDescPJ(proceedingJoinPoint);
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        }finally {
            log.debug("Time Consuming: {} ms", System.currentTimeMillis() - startTime);
        }
        log.debug("{}，Response result  : {}", aspectMethodLogDescPJ, JSON.toJSONString(result));

        return result;
    }

    /**
     * Gets aspect method log desc pj.
     *
     * @param proceedingJoinPoint the proceeding join point
     * @return the aspect method log desc pj
     * @throws Exception the exception
     * @author chen.cheng
     */
    public String getAspectMethodLogDescPJ(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        String targetName = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] arguments = proceedingJoinPoint.getArgs();
        return getAspectMethodLogDesc(targetName, methodName, arguments);
    }

    /**
     * Gets aspect method log desc.
     *
     * @param targetName the target name
     * @param methodName the method name
     * @param arguments  the arguments
     * @return the aspect method log desc
     * @throws Exception the exception
     * @author chen.cheng
     */
    public String getAspectMethodLogDesc(String targetName, String methodName, Object[] arguments) throws Exception {
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(Log.class).title());
                    break;
                }
            }
        }
        return description.toString();
    }

    /**
     * Do after.
     *
     * @param joinPoint the join point
     * @author chen.cheng
     */
    @After("logPointCut()")
    public void doAfter(JoinPoint joinPoint) {
        log.debug(">>>>>>>>>>>>>>>>>>> end <<<<<<<<<<<<<<<<<<<<<<<<" + LINE_SEPARATOR);
        // handleLog(joinPoint, e);
    }

    /**
     * Handle log.
     *
     * @param joinPoint the join point
     * @param e the e
     * @author chen.cheng
     */
    private void handleLog(JoinPoint joinPoint, Exception e) {
        try {
            // 获得注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }
            // 获得方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            String action = controllerLog.action();
            String title = controllerLog.title();
            // 打印日志，如有需要还可以存入数据库
            log.debug(">>>>>>>>>>>>>模块名称：{}", title);
            log.debug(">>>>>>>>>>>>>操作名称：{}", action);
            log.debug(">>>>>>>>>>>>>入口参数: {}", JSON.toJSONString(joinPoint.getArgs()));
            if (null != e) {
                log.debug(">>>>>>>>>>>>>{}->{}异常输出：{}", className, methodName, e.toString());
            }
        }
        catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
        }
    }

    /**
     * Gets annotation log.
     *
     * @param joinPoint the join point
     * @return the annotation log
     * @throws Exception the exception
     * @author chen.cheng
     */
    private static Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

}
