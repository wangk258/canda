package com.canda.test.concurrent;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author Wangkun
 * @CreateDate 2017/8/24 11:31
 */
@Slf4j
public abstract class BaseCountDownLatch implements Runnable {

    @Getter
    private String threadName;

    @Getter
    private boolean started = false;

    private CountDownLatch countDownLatch;

    public BaseCountDownLatch(CountDownLatch countDownLatch, String threadName) {
        this.countDownLatch = countDownLatch;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            threadStart();
            started = true;
        } catch (Throwable throwable) {
            started = false;
            throwable.printStackTrace(System.err);
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

    public abstract void threadStart();
}
