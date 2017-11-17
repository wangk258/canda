package com.canda.test.guava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @author Wangkun
 * @CreateDate 2017/8/30 10:58
 */
public class CollectionFilterTest {

    public static void main(String[] args) {
        filter();
    }

    private static void filter() {
        List<String> list = Lists.newArrayList();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        Map<String, String> map =  Maps.uniqueIndex(list, new Function<String, String>() {
            @Override
            public String apply(String string) {
                return string;
            }
        });
        if (map.containsKey("C")) {
            list = FluentIterable.from(list).filter(new Predicate<String>() {
                @Override
                public boolean apply(String s) {
                    return !s.equals("B");
                }
            }).toList();
            System.out.println(list.toString());
        }
        System.out.println(map.toString());
        System.out.println(list.toString());
    }
}
