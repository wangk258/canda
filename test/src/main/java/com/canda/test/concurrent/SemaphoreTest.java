package com.canda.test.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Wangkun
 * @CreateDate 2017/8/24 17:32
 */
public class SemaphoreTest {

    private static final Semaphore semaphore = new Semaphore(5);

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static void execute() {
        for (int i = 0; i < 20; i++) {
            final int n = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "等待执行");
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + "执行中ing");
                        Thread.sleep(5000 * n);
                        semaphore.release();
                        System.out.println(Thread.currentThread().getName() + "结束执行");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }

    public static void main(String[] args) {
        execute();
    }
}
