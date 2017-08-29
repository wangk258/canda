package com.canda.test.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author Wangkun
 * @CreateDate 2017/8/24 14:00
 */
public class ThreadOne extends BaseCountDownLatch {

    public ThreadOne(CountDownLatch countDownLatch) {
        super(countDownLatch, "threadOne");
    }

    @Override
    public void threadStart() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s has started!", this.getThreadName()));
    }
}
