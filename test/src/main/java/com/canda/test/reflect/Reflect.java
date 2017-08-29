package com.canda.test.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Wangkun
 * @CreateDate 2017/8/3 13:54
 */
public class Reflect {

    public static void main(String[] args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Proxy proxy = new Proxy();
        Method method = Proxy.class.getDeclaredMethod("run");
        method.invoke(proxy, null);

    }

    static class Proxy {

        public void run() {
            System.out.println("runing!");
        }
    }
}
