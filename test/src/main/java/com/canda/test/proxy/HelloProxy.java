package com.canda.test.proxy;

/**
 * @author kun.wang
 * @createDate 2017年1月24日 下午3:56:39
 */
public class HelloProxy implements Hello {
	
	private Hello hello;
	
	public HelloProxy() {
		this.hello = new HelloImpl();
	}

	@Override
	public void say(String name) {
		before();
		hello.say(name);
		after();
	}
	
	private void before() {
		System.out.println("before");
	}
	
	private void after() {
		System.out.println("after");
	}

	
	public static void main(String[] args) {
		HelloProxy proxy = new HelloProxy();
		proxy.say("Canda");
	}
}
