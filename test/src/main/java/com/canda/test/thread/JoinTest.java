package com.canda.test.thread;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Wangkun
 * @CreateDate 2017/8/30 16:57
 */
public class JoinTest {

    public static List<String> contents = Lists.newArrayList(
            "One", "Two", "Three", "Four");

    public static void main(String[] args) throws InterruptedException {
        long patienceTime = 1000 * 10;
        Bean bean = new Bean();
        printMessage("测试开始");
        long beginTime = System.currentTimeMillis();
        bean.start();
        bean.join(1000);
        while (bean.isAlive()) {
            printMessage("等待子线程结束");
            bean.join(1000);
            if (System.currentTimeMillis() - beginTime > patienceTime && bean.isAlive()) {
                printMessage("等待时间太长");
                bean.interrupt();
                bean.join();
            }
        }

    }

    static void printMessage(String message) {
        System.out.println(String.format("%s: %s", Thread.currentThread().getName(), message));
    }

    private static class Bean extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < contents.size(); i++) {
                    Thread.sleep(4000);
                    printMessage(contents.get(i));
                }
            } catch (InterruptedException e) {
                printMessage("Interrupted Exception");
            }
        }
    }
}
