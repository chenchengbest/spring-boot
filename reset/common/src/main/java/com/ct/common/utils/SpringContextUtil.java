package com.ct.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        setApplicationContextDeclare(arg0);
    }

    public static void setApplicationContextDeclare(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    // 获取上下文
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    // 通过名字获取上下文中的bean
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    // 通过类型获取上下文中的bean
    public static Object getBean(Class<?> requiredType) {
        return applicationContext.getBean(requiredType);
    }

}
