package com.canda.common.util;

import com.google.common.collect.Sets;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

/**
 * 对象占用的字节大小工具类
 * 参考：http://yueyemaitian.iteye.com/blog/2033046
 * @author Wangkun
 * @CreateDate 2017/7/6 15:15
 */
public class SizeOfObjectUtil {

    static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation instrumentation) {
        instrumentation = instrumentation;
    }

    /**
     * 直接计算当前对象占用的空间大小，包括当前类及超类的基本类型字段实例大小<br/>
     * 引用类型实例字段引用大小、实例基本类型数组总占用空间大小、实例引用类型数组引用本身占用空间大小<br/>
     * 但不包括超类继承下来的和当前类声明的实例引用字段的对象本身的大小、实例引用数组引用的对象本身的大小
     * 
     * @param obj
     * @return
     */
    public static long sizeOf(Object obj) {
        return instrumentation.getObjectSize(obj);
    }

    /**
     * 递归计算对象的总大小，包括当前类和超类的实例字段大小及实例字段引用对象大小
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static long fullOfSize(Object obj) throws IllegalAccessException {
        Set<Object> hasCount = Sets.newHashSet();
        Deque<Object> deque = new ArrayDeque<>();
        deque.add(obj);
        long fullSize = 0L;
        while (deque.size() > 0) {
            Object temp = deque.poll();
            fullSize += skipObject(hasCount, temp) ? 0L : sizeOf(temp);
            Class<?> tempClass = temp.getClass();
            if (tempClass.isArray()) {
                if (tempClass.getName().length() > 2) {
                    for (int i = 0; i < Array.getLength(temp); i++) {
                        Object element = Array.get(temp, i);
                        if (element != null) {
                            // 非基本类型需要深度变量其对象
                            deque.add(element);
                        }
                    }
                }
            } else {
                while (tempClass != null) {
                    Field[] fields = tempClass.getFields();
                    for (Field field : fields) {
                        //静态字段和基本类型不计
                        if (Modifier.isStatic(field.getModifiers()) || field.getType().isPrimitive()) {
                            continue;
                        }
                        field.setAccessible(true);
                        Object value = field.get(temp);
                        if (value == null) {
                            continue;
                        }
                        deque.add(value);
                    }
                    tempClass = tempClass.getSuperclass();
                }
            }
        }
        return fullSize;
    }

    /**
     * String.intern()的对象不计，计算过的不计，也避免死循环
     * 
     * @param hasCount
     * @param obj
     * @return
     */
    public static boolean skipObject(Set<Object> hasCount, Object obj) {
        if (obj instanceof String && obj == ((String) obj).intern()) {
            return true;
        }
        return hasCount.contains(obj);
    }
}
