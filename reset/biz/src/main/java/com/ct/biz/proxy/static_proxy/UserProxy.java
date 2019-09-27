package com.ct.biz.proxy.static_proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chen.cheng on 2019/9/27.
 */
public class UserProxy implements IUserService {

    Logger logger = LoggerFactory.getLogger(UserProxy.class);

    private IUserService target;

    public IUserService getTarget() {
        return target;
    }

    public void setTarget(IUserService target) {
        this.target = target;
    }

    @Override
    public void say() {
        logger.info("before");
        this.target.say();
        logger.info("after");
    }
}
