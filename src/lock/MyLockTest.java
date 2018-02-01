package lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ����1��lock()����ȷʹ�÷���
 * @author HY
 *
 */
public class MyLockTest {
	private static ArrayList<Integer> arrayList = new ArrayList<Integer>();
	private static Lock lock = new ReentrantLock(); //ע�����ʹ��Lockʵ���࣬��������
	
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				Thread thread = Thread.currentThread();
				lock.lock();//����
				
				try {
					System.out.println(thread.getName() + "�õ�����");
					for (int i=0; i<5; i++) {
						arrayList.add(i);
					}
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					lock.unlock();
					System.out.println(thread.getName() + "�ͷ�����");
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				Thread thread = Thread.currentThread();
				lock.lock();//����
				
				try {
					System.out.println(thread.getName() + "�õ�����");
					for (int i=0; i<5; i++) {
						arrayList.add(i);
					}
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					lock.unlock();
					System.out.println(thread.getName() + "�ͷ�����");
				}
			}
		}.start();
	}
}
