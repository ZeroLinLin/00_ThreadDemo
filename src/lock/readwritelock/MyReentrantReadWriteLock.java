package lock.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyReentrantReadWriteLock {
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	/**
	 * ������,�ö���������
	 * @param thread
	 */
	public void get(Thread thread) {
		readWriteLock.readLock().lock();
		try {
			long start = System.currentTimeMillis();
            
            while(System.currentTimeMillis() - start <= 1000) {
                System.out.println(thread.getName()+"���ڽ��ж�����");
                Thread.sleep(100);
            }
            System.out.println(thread.getName()+"���������");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			readWriteLock.readLock().unlock();
		}
	}
	/**
	 * д��������д��������
	 * @param thread
	 */
	public void write(Thread thread) {
		readWriteLock.writeLock().lock();
		try {
			long start = System.currentTimeMillis();
            
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"���ڽ���д����");
            }
            System.out.println(thread.getName()+"д�������");
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
