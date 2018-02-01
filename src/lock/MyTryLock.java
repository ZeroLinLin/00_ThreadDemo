package lock;
/**
 * 例子2，tryLock()的使用方法
 * 观察现象：一个线程获得锁后，另一个线程取不到锁，不会一直等待
 * @author HY
 *
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTryLock {
	private static Lock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				Thread thread = Thread.currentThread();
				boolean tryLock = lock.tryLock(); //非阻塞
				if (tryLock) {
					try {
						//Thread.sleep(2000); // 打开这里，另个线程会获取锁失败
						
						System.out.println(thread.getName() + "获得了锁");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						lock.unlock();
						System.out.println(thread.getName() + "释放了锁");
					}
				} else {
					System.out.println(thread.getName() + "获取锁失败");
				}
				
			}
		}.start();
		
		new Thread() {
			public void run() {
				Thread thread = Thread.currentThread();
				boolean tryLock = lock.tryLock(); //非阻塞式
				if (tryLock) {
					try {
						System.out.println(thread.getName() + "获得了锁");
						
					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						lock.unlock();
						System.out.println(thread.getName() + "释放了锁");
					}
				} else {					
					System.out.println(thread.getName() + "获取锁失败");
				}
			};
		}.start();
	}
}
