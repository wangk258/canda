package com.canda.common.test;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.collect.Lists;

/**
 * Lock测试
 * @author kun.wang
 * @createDate 2016年12月26日 上午10:25:03
 * 
 * Lock和synchronized有以下几点不同：
 * 1）Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
 * 2）synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
 * 3）Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
 * 4）通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
 * 5）Lock可以提高多个线程进行读操作的效率。
 */
@SuppressWarnings("unused")
public class LockTest {
	
	private Lock lock = new ReentrantLock();
	
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	public static void main(String[] args) {
		final LockTest lockTest = new LockTest();
//		lockTest.lock();
//		lockTest.tryLock();
//		lockTest.lockInterruptibly();
		lockTest.reentrantReadWriteLock();
	}
	
	private void reentrantReadWriteLock() {
		final LockTest lockTest = new LockTest();
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				lockTest.doReentrantReadWriteLock(new Thread());
			}
		};
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				lockTest.doReentrantReadWriteLock(new Thread());
			}
		};
		thread1.start();
		thread2.start();
	}
	
	private void doReentrantReadWriteLock(Thread thread) {
		rwl.readLock().lock();
		try {
			for (int i = 1; i < 10; i++) {
				System.out.println(String.format("%s线程正在进行第%d次读操作", thread.getName(), i));
			}
		} finally {
			System.out.println(String.format("%s线程完成读操作", thread.getName()));
			rwl.readLock().unlock();
		}
	}
	
	private void lockInterruptibly() {
		final LockTest lockTest = new LockTest();
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				try {
					lockTest.doLockInterruptibly(Thread.currentThread());
				} catch (InterruptedException e) {
					System.out.println(String.format("%s线程被中断", Thread.currentThread().getName()));
				}
			}
		};
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				try {
					lockTest.doLockInterruptibly(Thread.currentThread());
				} catch (InterruptedException e) {
					System.out.println(String.format("%s线程被中断", Thread.currentThread().getName()));
				}
			}
		};
		
		thread1.start();
		thread2.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thread2.interrupt();
	}
	
	private void doLockInterruptibly(Thread thread) throws InterruptedException {
		lock.lockInterruptibly();
		try {
			System.out.println(String.format("%s线程得到了锁", thread.getName()));
			Integer i = 0;
			for (;;i++) {
				if (i >= 1000000000) {
					break;
				}
			}
		} finally {
			System.out.println(String.format("%s线程执行finally", thread.getName()));
			lock.unlock();
			System.out.println(String.format("%s线程释放了锁", thread.getName()));
		}
	}
	
	private void tryLock() {
		final LockTest lockTest = new LockTest();
		new Thread() {
			public void run() {
				lockTest.doTryLock(Thread.currentThread());
			};
		}.start();
		
		new Thread() {
			public void run() {
				lockTest.doTryLock(Thread.currentThread());
			};
		}.start();
	}
	
	private void doTryLock(Thread thread) {
		if (lock.tryLock()) {
			try {
				System.out.println(String.format("%s线程得到了锁", thread.getName()));
				List<Integer> list = Lists.newArrayList();
				for (int i = 0; i < 10000000; i++) {
					list.add(i);
				}
			} catch(Exception exception) {
				//解决异常
			} finally {
				System.out.println(String.format("%s线程释放了锁", thread.getName()));
				lock.unlock();
			}
		} else {
			System.out.println(String.format("%s线程获取锁失败", thread.getName()));
		}
	}
	
	private void lock() {
		final LockTest lockTest = new LockTest();
		new Thread() {
			public void run() {
				lockTest.doLock(Thread.currentThread());
			};
		}.start();
		
		new Thread() {
			public void run() {
				lockTest.doLock(Thread.currentThread());
			};
		}.start();
	}
	
	private void doLock(Thread thread) {
		lock.lock();
		try {
			System.out.println(String.format("%s线程得到了锁", thread.getName()));
			List<Integer> list = Lists.newArrayList();
			for (int i = 0; i < 10000000; i++) {
				list.add(i);
			}
		} catch(Exception exception) {
			//解决异常
		} finally {
			System.out.println(String.format("%s线程释放了锁", thread.getName()));
			lock.unlock();
		}
	}
	
	
}
