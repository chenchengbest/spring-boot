package com.ct.biz.proxy.static_proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chen.cheng on 2019/9/27.
 */
public class UserService implements IUserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Override
    public void say() {
       logger.info("user say!");
    }
}
