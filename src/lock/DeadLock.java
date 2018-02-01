package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
	public static void main(String[] args) {
		Lock lock1 = new ReentrantLock();
		Lock lock2 = new ReentrantLock();
		
		new Thread() {
			@Override
			public void run() {
				lock1.lock();
				try {
					Thread.sleep(50);
					
					lock2.lock();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock1.unlock();
					lock2.unlock();
				}
			}
		}.start();
		
		new Thread() {
			@Override
			public void run() {
				lock2.lock();
				lock1.lock();
				//lock1.tryLock();  //这里使用tryLock()就不会发生死锁
				try {
					
				} finally {
					lock2.unlock();
					lock1.unlock();
				}
			}
		}.start();
	}
}
