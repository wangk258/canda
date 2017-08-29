package com.canda.test.guava;

import com.google.common.base.Preconditions;

/**
 * @author Wangkun
 * @CreateDate 2017/8/29 13:25
 */
public class ProconditionsTest {

    public static void main(String[] args) {
        test1(null);
    }

    private static void test(Integer value) {
        Preconditions.checkNotNull(value, "value is null");
    }

    private static void test1(Integer value) {
        Preconditions.checkArgument(value != null, "value is null");
    }

}
