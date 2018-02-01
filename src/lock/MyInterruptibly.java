package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ����3��lockInterruptibly()��Ӧ�жϵ�ʹ�÷���
 * @author HY
 *
 */
public class MyInterruptibly {
	private static Lock lock = new ReentrantLock();
	
	public void insert(Thread thread) throws InterruptedException {
		lock.lockInterruptibly(); //ע�⣬�����Ҫ��ȷ�жϵȴ������̣߳����뽫��ȡ���������棬Ȼ��InterruptedException�׳�
		try {
			System.out.println(thread.getName()+"�õ�����");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //��������
            }
		} finally {
			System.out.println(Thread.currentThread().getName()+"ִ��finally");
            lock.unlock();
            System.out.println(thread.getName()+"�ͷ�����");
		}
	}
	
	public static void main(String[] args) {
		MyInterruptibly test = new MyInterruptibly();
		MyThread thread1 = new MyThread(test);
		MyThread thread2 = new MyThread(test);
		thread1.start();
		thread2.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thread2.interrupt();//�����жϷ����������ܷ��жϵȴ��е��߳�
		System.out.println("over!");
	}
}

class MyThread extends Thread {
	MyInterruptibly test = null;
	public MyThread(MyInterruptibly test) {
		super();
		this.test = test;
	}

	@Override
	public void run() {
		try {
			test.insert(Thread.currentThread());
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName()+"��" + Thread.currentThread().getName() +"�ж�");
		}
	}
	
	@Override
	public void interrupt() {
		// TODO Auto-generated method stub
		super.interrupt();
		System.out.println(this.getName() + "111��" + Thread.currentThread().getName() +"�ж�");
	}
}

/*
Thread-0�õ�����
Thread-1��main�ж�
over!
Thread-1��Thread-1�ж�
 */
