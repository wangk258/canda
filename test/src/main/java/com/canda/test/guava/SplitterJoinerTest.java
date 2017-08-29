package com.canda.test.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Wangkun
 * @CreateDate 2017/8/29 16:49
 */
public class SplitterJoinerTest {

    public static void main(String[] args) {
        splitter();
        joiner();
    }

    private static void splitter() {
        String str = ",,a,b, ,c ,, d ,e,f,,";
        List<String> list = Splitter.on(",").omitEmptyStrings().splitToList(str);
        System.out.println(list.toString());
    }

    private static void joiner() {
        List<String> list = Lists.newArrayList(
                "a",
                "b",
                "",
                "d",
                null,
                "f",
                "g",
                "h"
        );
        String string = Joiner.on(",").skipNulls().join(list);
        System.out.println(string);
    }
}
