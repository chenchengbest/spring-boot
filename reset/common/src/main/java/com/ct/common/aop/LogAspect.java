package com.ct.common.aop;

import com.ct.common.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * The type Log aspect.
 *
 * @author chen.cheng
 */
@Aspect
@Component("logAspect")
public class LogAspect {
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
    @AfterReturning(pointcut = "logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        handleLog(joinPoint, null);
    }

    /**
     * Do after.
     *
     * @param joinPoint the join point
     * @param e         the e
     * @author chen.cheng
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e);
    }

    /**
     * Handle log.
     *
     * @param joinPoint the join point
     * @param e         the e
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
            //打印日志，如有需要还可以存入数据库
            log.info(">>>>>>>>>>>>>模块名称：{}", title);
            log.info(">>>>>>>>>>>>>操作名称：{}", action);
            if (null != e) {
                log.info(">>>>>>>>>>>>>{}->{}异常输出：{}", className, methodName, e.toString());
            }
        } catch (Exception exp) {
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
