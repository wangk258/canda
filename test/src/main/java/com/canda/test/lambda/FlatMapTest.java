package com.canda.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Wangkun
 * @date 2017/11/27 16:53
 */
public class FlatMapTest {

    public static void main(String[] args) {
        /**
         * 初始化各个公司员工数量
         */
        List companies = Lists.newArrayList(
                new Company("A", Lists.newArrayList(new Employee("a", 3), new Employee("b", 4), new Employee("c", 2))),
                new Company("B", Lists.newArrayList(new Employee("h", 8), new Employee("i", 2), new Employee("j", 6))),
                new Company("C", Lists.newArrayList(new Employee("o", 9), new Employee("p", 10), new Employee("q", 5))),
                new Company("D", Lists.newArrayList(new Employee("x", 7), new Employee("y", 4), new Employee("z", 2))));

        test1(companies);
        test2();
    }

    private static void test2() {
        int[] intArray = {1, 2, 3, 4, 5, 6};
        Stream<int[]> streamArray = Stream.of(intArray);
        IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x));
        intStream.forEach(x -> System.out.println(x));
        System.out.println("------------------");
        Stream.of(intArray).flatMapToInt(x -> IntStream.of(x)).forEach(x -> System.out.println(x));
    }

    /**
     * 获取所有公司中工龄3、4年的员工
     * 
     * @param companies
     */
    private static void test1(List<Company> companies) {
       companies.stream().map(company -> company.getEmployeeList())
            .flatMap(employees -> employees.stream())
            .filter(employee -> employee.getAge() == 3 || employee.getAge() ==4)
            .map(employee -> String.format("name=%s,age=%s", employee.getName(), employee.getAge()))
            .forEach(System.out::println);
    }


    @Data
    @AllArgsConstructor
    static class Company {

        private String name;

        private List<Employee> employeeList;

    }

    @Data
    @AllArgsConstructor
    static class Employee {

        private String name;

        private Integer age;

    }

}
