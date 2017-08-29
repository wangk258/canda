package com.canda.test.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Wangkun
 * @CreateDate 2017/8/29 11:06
 */
public class SyschronizedObjectTest {

    public static void main(String[] args) {
        int size = 4;
        final Executor executor = Executors.newFixedThreadPool(size);
        final Bean bean = new Bean();
        for (int i = 0; i < size; i++) {
            final int index = i + 1;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        bean.read("线程" + index + "   " + bean.hashCode(), bean);
                        bean.write("线程" + index + "   " + bean.hashCode(), bean);
                        bean.close("线程" + index + "   " + bean.hashCode());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    private static class Bean {
        private final byte[] lock = new byte[0];

        public void read(String threadName, Bean bean) throws InterruptedException {
            synchronized (bean) {
                System.out.println(threadName + "线程 read start");
                Thread.sleep(1000);
                System.out.println(threadName + "线程 read end");
            }
        }

        public void write(String threadName, Bean bean) throws InterruptedException {
            synchronized (bean) {
                System.out.println(threadName + "线程 write start");
                Thread.sleep(2000);
                System.out.println(threadName + "线程 write end");
            }
        }

        public void close(String threadName) throws InterruptedException {
            System.out.println(threadName + "线程 close start");
            Thread.sleep(1500);
            System.out.println(threadName + "线程 close end");
        }


    }
}
