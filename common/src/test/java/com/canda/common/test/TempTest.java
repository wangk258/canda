package com.canda.common.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 临时测试类
 * @author kun.wang
 * @createDate 2017年1月24日 上午10:11:47
 */
public class TempTest {
	
	//可重入锁
	final ReentrantLock lock = new ReentrantLock();
	final Condition condition1 = lock.newCondition();
	final Condition condition2 = lock.newCondition();
	final Condition condition3 = lock.newCondition();
	
	public static void main(String[] args) throws ClassNotFoundException {
		TempTest temp = new TempTest();
//		temp.classTest();
		temp.joinTest();;
	}
	
	private void classTest() throws ClassNotFoundException {
		TempTest temp = new TempTest();
		Class<?> clazz1 = Class.forName(temp.getClass().getName());
		System.out.println(clazz1.getName());
		Class<?> clazz2 = TempTest.class;
		System.out.println(clazz2.getName());
		Class<?> clazz3 = temp.getClass();
		System.out.println(clazz3.getName());
	}
	
	private void joinTest() {
		final Value value = new Value(1);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				synchronized (value) {
					for (int i = 0; i < 5; i++) {
						if (value.getValue() <= 75) {
							System.out.println(Thread.currentThread().getName() + value.getValue());
							value.setValue(value.getValue() + 1);
						}
					}
				}
			};
		};
		Thread thread1 = new Task(1, value);
		thread1.setName("线程1：");
		Thread thread2 = new Task(1, value);
		thread2.setName("线程2：");
		Thread thread3 = new Task(1, value);
		thread3.setName("线程3：");
		try {
			thread1.start();
			thread1.join();
			thread2.start();
			thread2.join();
			thread3.start();
			thread3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	class Task extends Thread {
		
		private Integer threadNo;
		
		private Value value;
		
		public Task(Integer threadNo, Value value) {
			this.threadNo = threadNo;
			this.value = value;
		}
		
		@Override
		public void run() {
			synchronized (value) {
				for (int i = 0; i < 5; i++) {
					if (value.getValue() <= 75) {
						System.out.println(Thread.currentThread().getName() + value.getValue());
						value.setValue(value.getValue() + 1);
					}
					value.setThreadNo(getThreadNo());
				}
			}
		}

		public Integer getThreadNo() {
			return threadNo;
		}

		public void setThreadNo(Integer threadNo) {
			this.threadNo = threadNo;
		}

		public Value getValue() {
			return value;
		}

		public void setValue(Value value) {
			this.value = value;
		}
	}
	
	class Value {
		
		private Integer value;
		
		private Integer threadNo = 1;
		
		public Value(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public Integer getThreadNo() {
			return threadNo;
		}

		public void setThreadNo(Integer threadNo) {
			this.threadNo = threadNo;
		}
	}

}
