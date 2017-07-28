package com.canda.test.proxy;

/**
 * @author kun.wang
 * @createDate 2017年1月24日 下午3:55:54
 */
public class HelloImpl implements Hello {

	@Override
	public void say(String name) {
		System.out.println("hello: " + name);
	}

}
