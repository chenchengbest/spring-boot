package com.ct.biz.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageEventListener implements EventListener {
    private Logger logger = LoggerFactory.getLogger(MessageEventListener.class);
    @Override
    public void doEvent(Object result) {
       logger.info(">>>>>>>接收到message，{}", result);
    }
}
