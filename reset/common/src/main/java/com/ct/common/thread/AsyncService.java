package com.ct.common.thread;

import org.springframework.stereotype.Repository;

/**
 * The interface Async service.
 *
 * @author chen.cheng
 */
@Repository
public interface AsyncService {
    /**
     * Execute async.
     *
     * @author chen.cheng
     */
    void executeAsync();
}
