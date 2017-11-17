package com.canda.test.lambda;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.common.collect.Lists;

/**
 * @author Wangkun
 * @CreateDate 2017/11/6 16:48
 */
public class FunctionalTest {

    @FunctionalInterface
    interface Convert<F, T> {
        T convert(F from);
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    private static void test1() {
        Convert<String, Integer> convert = from -> Integer.valueOf(from);
        Integer result = convert.convert("232323");
        System.out.println(result);
    }

    private static void test2() {
        Convert<String, Integer> convert = Integer::valueOf;
        Integer result = convert.convert("223415665");
        System.out.println(result);
    }

    private static void test3() {
        Runnable runnable = () -> {
            System.out.println("Test111");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Test222");
        };
        new Thread(runnable).start();
        new Thread(() -> System.out.println("TestTest")).start();
    }

    private static void test4() {
        donation(10, money -> System.out.println("捐赠了" + money + "元钱"));
    }

    private static void donation(Integer money, Consumer<Integer> consumer) {
        consumer.accept(money);
    }

    private static void test5() {
        List<Integer> list = supply(10, () -> new Random().nextInt());
        list.forEach(System.out::print);
    }

    private static List<Integer> supply(Integer num, Supplier<Integer> supplier) {
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

}
