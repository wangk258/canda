package com.canda.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射工具类
 * @author kun.wang
 * @createDate 2017年1月23日 下午5:37:33
 */
public class ReflectionUtil {
	
	private static final Logger log = LoggerFactory.getLogger(ReflectionUtil.class);
	
	/**
	 * 创建实例
	 * @createDate 2017年1月23日 下午5:40:08
	 * @author kun.wang
	 * @param clazz
	 * @return
	 * @return Object
	 */
	public static Object newInstance(Class<?> clazz) {
		Object instance;
		try {
			instance = clazz.newInstance();
		} catch (InstantiationException e) {
			log.error("new instance error", e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			log.error("new instance error", e);
			throw new RuntimeException(e);
		}
		return instance;
	}
	
	/**
	 * 调用方法
	 * @createDate 2017年1月23日 下午5:46:37
	 * @author kun.wang
	 * @param clazz
	 * @param method
	 * @param args
	 * @return
	 * @return Object
	 */
	public static Object invokeMethod(Class<?> clazz, Method method, Object... args) {
		Object result;
		try {
			method.setAccessible(true);
			result = method.invoke(clazz, args);
		} catch (IllegalAccessException e) {
			log.error("invoke method error", e);
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			log.error("invoke method error", e);
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			log.error("invoke method error", e);
			throw new RuntimeException(e);
		}
		return result;
	}
	
	/**
	 * 设置成员变量的值
	 * @createDate 2017年1月23日 下午5:49:06
	 * @author kun.wang
	 * @param clazz
	 * @param field
	 * @param value
	 * @return void
	 */
	public static void setFieldValue(Class<?> clazz, Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(clazz, value);
		} catch (IllegalArgumentException e) {
			log.error("set field value error", e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			log.error("set field value error", e);
			throw new RuntimeException(e);
		}
		
	}
}
