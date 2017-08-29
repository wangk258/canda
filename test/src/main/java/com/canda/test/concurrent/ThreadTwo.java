package com.canda.test.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author Wangkun
 * @CreateDate 2017/8/24 14:04
 */
public class ThreadTwo extends BaseCountDownLatch {

    public ThreadTwo(CountDownLatch countDownLatch) {
        super(countDownLatch, "threadTwo");
    }

    @Override
    public void threadStart() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s has started!", getThreadName()));
    }
}
