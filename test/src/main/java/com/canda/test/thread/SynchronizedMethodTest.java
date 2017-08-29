package com.canda.test.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Wangkun
 * @CreateDate 2017/8/29 10:26
 */
public class SynchronizedMethodTest {

    public static void main(String[] args) throws InterruptedException {
        int size = 4;
        Executor executor = Executors.newFixedThreadPool(size);
        final Bean bean = new Bean();
        for (int i = 0; i < 4; i++) {
            final int index = i + 1;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        bean.read("线程" + index + "  " + bean.hashCode());
                        bean.write("线程" + index + "  " + bean.hashCode());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static class Bean {

        public synchronized void read(String threadName) throws InterruptedException {
            System.out.println(threadName + "线程 read start");
            Thread.sleep(1000);
            System.out.println(threadName + "线程 read end");
        }

        public synchronized void write(String threadName) throws InterruptedException {
            System.out.println(threadName + "线程 write start");
            Thread.sleep(2000);
            System.out.println(threadName + "线程 write end");
        }

    }
}
