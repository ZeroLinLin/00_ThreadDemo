package lock.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyReentrantReadWriteLock {
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	/**
	 * 读操作,用读锁来锁定
	 * @param thread
	 */
	public void get(Thread thread) {
		readWriteLock.readLock().lock();
		try {
			long start = System.currentTimeMillis();
            
            while(System.currentTimeMillis() - start <= 1000) {
                System.out.println(thread.getName()+"正在进行读操作");
                Thread.sleep(100);
            }
            System.out.println(thread.getName()+"读操作完毕");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			readWriteLock.readLock().unlock();
		}
	}
	/**
	 * 写操作，用写锁来锁定
	 * @param thread
	 */
	public void write(Thread thread) {
		readWriteLock.writeLock().lock();
		try {
			long start = System.currentTimeMillis();
            
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"正在进行写操作");
            }
            System.out.println(thread.getName()+"写操作完毕");
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}
	
	public static void main(String[] args) {
		final MyReentrantReadWriteLock test = new MyReentrantReadWriteLock(); 
		
		new Thread() {
			@Override
			public void run() {
				test.get(this);
				test.write(this);
			}
		}.start();
		
		new Thread() {
			@Override
			public void run() {
				test.get(this);
				test.write(this);
			}
		}.start();
	}
}
