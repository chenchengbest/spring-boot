package com.ct.biz.strategy;

import com.ct.common.annotation.Log;
import com.ct.common.annotation.VipStrategy;
import com.ct.common.exception.BusinessException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Vip strategy resolver.
 *
 * @author chen.cheng
 */
@Component
public class VipStrategyResolver implements InitializingBean, ApplicationContextAware {
    /**
     * The Application context.
     *
     * @author chen.cheng
     */
    private ApplicationContext applicationContext;

    /**
     * The Vip strategy resolver map.
     *
     * @author chen.cheng
     */
    private Map<String, IVipStrategy> vipStrategyResolverMap = new HashMap<>();

    /**
     * After properties set.
     *
     * @throws Exception the exception
     * @author chen.cheng
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(VipStrategy.class);
        for (String key : beanMap.keySet()) {
            VipStrategy animation = beanMap.get(key).getClass().getAnnotation(VipStrategy.class);
            vipStrategyResolverMap.put(animation.name(), (IVipStrategy) beanMap.get(key));
        }
    }

    /**
     * Sets application context.
     *
     * @param applicationContext the application context
     * @throws BeansException the beans exception
     * @author chen.cheng
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * Gets handler.
     *
     * @param vipCode the vip code
     * @return the handler
     * @author chen.cheng
     */
    @Log(title = "策略器门面",action = "getHandler")
    public IVipStrategy getHandler(String vipCode) throws BusinessException {
        if (!vipStrategyResolverMap.containsKey(vipCode)) {
            throw new BusinessException(String.format("不存在类型为：{%s}策略", vipCode));
        }
        return vipStrategyResolverMap.get(vipCode);
    }
}
