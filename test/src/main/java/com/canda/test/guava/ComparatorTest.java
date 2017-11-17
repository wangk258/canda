package com.canda.test.guava;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author Wangkun
 * @CreateDate 2017/8/29 17:56
 */
public class ComparatorTest {

    public static void main(String[] args) {
        List<User> list = Lists.newArrayList();
        list.add(new User(3, "A"));
        list.add(new User(6, "B"));
        list.add(new User(1, "C"));
        list.add(new User(5, "D"));
        list.add(new User(2, "E"));
        Ordering<User> ordering = Ordering.natural().nullsLast().onResultOf(new Function<User, Comparable>() {
            @Override
            public Comparable apply(User user) {
                return user.getAge();
            }
        });
        System.out.println(list.toString());
    }

    @AllArgsConstructor
    private static class User {

        @Getter
        private int age;

        @Getter
        private String name;

        @Override
        public String toString() {
            return super.toString() + "[name: " + this.name + "  age: " + this.age + "]";
        }
    }

}
