package com.canda.test.concurrent;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Wangkun
 * @CreateDate 2017/8/24 14:07
 */
public class CountDownLatchTest {

    public static boolean execute() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        List<BaseCountDownLatch> list = Lists.newArrayList();
        list.add(new ThreadOne(countDownLatch));
        list.add(new ThreadTwo(countDownLatch));
        list.add(new ThreadThree(countDownLatch));
        list.add(new ThreadFour(countDownLatch));
        Executor executor = Executors.newFixedThreadPool(list.size());
        for (BaseCountDownLatch baseCountDownLatch : list) {
            executor.execute(baseCountDownLatch);
        }
        countDownLatch.await();
        for (BaseCountDownLatch baseCountDownLatch : list) {
            if (!baseCountDownLatch.isStarted()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("begin");
        boolean execute = execute();
        System.out.println(execute);
        System.out.println("end");
    }

}
