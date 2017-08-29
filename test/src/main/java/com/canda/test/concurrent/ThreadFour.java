package com.canda.test.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author Wangkun
 * @CreateDate 2017/8/24 14:04
 */
public class ThreadFour extends BaseCountDownLatch {

    public ThreadFour(CountDownLatch countDownLatch) {
        super(countDownLatch, "threadFour");
    }

    @Override
    public void threadStart() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s has started!", getThreadName()));
    }
}
