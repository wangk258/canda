package com.canda.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 * @author kun.wang
 * @createDate 2017年1月24日 下午3:52:11
 */
public class DynamicProxy implements InvocationHandler {
	
	private Object target;

	public DynamicProxy(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result;
		before();
		result = method.invoke(target, args);
		after();
		return result;
	}
	
	private void before() {
		System.out.println("before");
	}
	
	private void after() {
		System.out.println("after");
	}

	public static void main(String[] args) {
		Hello hello = new HelloImpl();
		DynamicProxy proxy = new DynamicProxy(hello);
		Hello helloProxy = (Hello)Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), proxy);
		helloProxy.say("Jerry");
	}
	
}
