package com.canda.test.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author Wangkun
 * @CreateDate 2017/8/24 14:04
 */
public class ThreadThree extends BaseCountDownLatch {

    public ThreadThree(CountDownLatch countDownLatch) {
        super(countDownLatch, "threadThree");
    }

    @Override
    public void threadStart() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s has started!", getThreadName()));
    }
}
