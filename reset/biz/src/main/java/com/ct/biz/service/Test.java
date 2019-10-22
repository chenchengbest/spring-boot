package com.ct.biz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * The type Test.
 *
 * @author chen.cheng
 */
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static int count = 0;


    /**
     * The entry point of application.
     * cas(compare and swap in)
     * 当前持有的值和底层取到的值相等才能处置（死循环一直操作）
     * CAS （AB =>A CASReference 改变对象版本 来判断是不是 原始对象索引）
     * synchronize 作用于方法，那么影响的是类的同一个实例，那么不同的实例之间调用同一个函数不受影响
     * synchronize 作用于类或者静态方法，那么影响的是类
     *
     * volatile 可以 作为多线程之间的信号标记，用于判断多线程之间的对象是否被初始化，
     *对变量的写操作不依赖于当前值。
     *该变量没有包含在具有其他变量的不变式中。
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     * @author chen.cheng
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(500);
        final CountDownLatch countDownLatch = new CountDownLatch(2000);

        for (int i = 0; i < 2000;
             i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception ex) {
                    logger.info("exception {}", ex.toString());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        logger.info("count:{}", count);
    }

    private static void add() {
        count++;
    }

}
