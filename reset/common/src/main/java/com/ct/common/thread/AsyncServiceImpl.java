package com.ct.common.thread;

import com.ct.common.annotation.Log;
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
     * Execute async.
     *
     * @author chen.cheng
     */
    @Override
    @Async("asyncServiceExecutor")
    @Log(title = "AsyncServiceImpl",action = "executeAsync")
    public void executeAsync() throws Exception {
        Thread.sleep(10000);
    }
}
