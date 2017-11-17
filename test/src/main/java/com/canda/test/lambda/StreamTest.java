package com.canda.test.lambda;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

/**
 * @author Wangkun
 * @CreateDate 2017/11/8 15:08
 */
public class StreamTest {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        List<String> list = Lists.newArrayList("1One", "Two", "3Three", "Four");
        List<String> temp = list.stream().filter(str -> !Character.isDigit(str.charAt(0))).map(String::toUpperCase)
                .collect(Collectors.toList());
        temp.forEach(s -> System.out.print(s + "  "));
    }

    private static void test1() {
        List<String> list = Lists.newArrayList("1One", "Two", "3Three", "Four");
        list.stream().filter(str -> Character.isDigit(str.charAt(0))).forEach(str -> System.out.println(str));
    }
}
