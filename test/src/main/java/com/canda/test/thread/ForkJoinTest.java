package com.canda.test.thread;

import lombok.Data;

import java.util.concurrent.*;

/**
 * <p>
 * 参考地址：http://www.infoq.com/cn/articles/fork-join-introduction
 * </p>
 * 
 * @author Wangkun
 * @CreateDate 2017/11/16 15:14
 */
public class ForkJoinTest {

    private static final long start = 1;
    private static final long end = 1000000000;

    public static void main(String[] args) {
        testForkJoin();
        testFor();
    }

    private static void testFor() {
        long currentDate = System.currentTimeMillis();
        long sum = 0;
        for (long i = start; i <= end; i++) {
            sum += i;
        }
        System.out.println("value：" + sum + "   for time: " + (System.currentTimeMillis() - currentDate));
    }

    private static void testForkJoin() {
        long currentDate = System.currentTimeMillis();
        CountTask countTask = new CountTask(start, end);
        ForkJoinPool pool = new ForkJoinPool();
        Future<Long> future = pool.submit(countTask);
        try {
            System.out.println(
                    "value：" + future.get() + "   fork join time: " + (System.currentTimeMillis() - currentDate));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * RecursiveAction：用于没有返回结果的任务。<br/>
     * RecursiveTask ：用于有返回结果的任务。
     * </p>
     */
    @Data
    static class CountTask extends RecursiveTask<Long> {

        private static int THRESHOLD = 2;

        private long start;

        private long end;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            // 是否可以计算
            boolean canCompute = (end - start) <= THRESHOLD;
            if (canCompute) {
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                long middle = (end + start) / 2;
                CountTask left = new CountTask(start, middle);
                CountTask right = new CountTask(middle + 1, end);
                left.fork();
                right.fork();
                sum = left.join() + right.join();
            }
            return sum;
        }
    }

}
