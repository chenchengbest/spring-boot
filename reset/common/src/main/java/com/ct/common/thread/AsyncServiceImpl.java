package com.ct.common.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * The type Async service.
 *
 * @author chen.cheng
 */
@Service
public class AsyncServiceImpl implements AsyncService{
    /**
     * The Logger.
     *
     * @author chen.cheng
     */
    private Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    /**
     * Execute async.
     *
     * @author chen.cheng
     */
    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        logger.info("start executeAsync");
        try{
            Thread.sleep(10000);
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("end executeAsync");
    }
}
