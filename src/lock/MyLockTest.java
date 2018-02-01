package lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 例子1，lock()的正确使用方法
 * @author HY
 *
 */
public class MyLockTest {
	private static ArrayList<Integer> arrayList = new ArrayList<Integer>();
	private static Lock lock = new ReentrantLock(); //注意这里，使用Lock实现类，可重入锁
	
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				Thread thread = Thread.currentThread();
				lock.lock();//阻塞
				
				try {
					System.out.println(thread.getName() + "得到了锁");
					for (int i=0; i<5; i++) {
						arrayList.add(i);
					}
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					lock.unlock();
					System.out.println(thread.getName() + "释放了锁");
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				Thread thread = Thread.currentThread();
				lock.lock();//阻塞
				
				try {
					System.out.println(thread.getName() + "得到了锁");
					for (int i=0; i<5; i++) {
						arrayList.add(i);
					}
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					lock.unlock();
					System.out.println(thread.getName() + "释放了锁");
				}
			}
		}.start();
	}
}
