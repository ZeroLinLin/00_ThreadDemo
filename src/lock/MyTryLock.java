package lock;
/**
 * ����2��tryLock()��ʹ�÷���
 * �۲�����һ���̻߳��������һ���߳�ȡ������������һֱ�ȴ�
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
				boolean tryLock = lock.tryLock(); //������
				if (tryLock) {
					try {
						//Thread.sleep(2000); // ���������̻߳��ȡ��ʧ��
						
						System.out.println(thread.getName() + "�������");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						lock.unlock();
						System.out.println(thread.getName() + "�ͷ�����");
					}
				} else {
					System.out.println(thread.getName() + "��ȡ��ʧ��");
				}
				
			}
		}.start();
		
		new Thread() {
			public void run() {
				Thread thread = Thread.currentThread();
				boolean tryLock = lock.tryLock(); //������ʽ
				if (tryLock) {
					try {
						System.out.println(thread.getName() + "�������");
						
					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						lock.unlock();
						System.out.println(thread.getName() + "�ͷ�����");
					}
				} else {					
					System.out.println(thread.getName() + "��ȡ��ʧ��");
				}
			};
		}.start();
	}
}
