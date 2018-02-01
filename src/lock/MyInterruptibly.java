package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 例子3，lockInterruptibly()响应中断的使用方法
 * @author HY
 *
 */
public class MyInterruptibly {
	private static Lock lock = new ReentrantLock();
	
	public void insert(Thread thread) throws InterruptedException {
		lock.lockInterruptibly(); //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
		try {
			System.out.println(thread.getName()+"得到了锁");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //插入数据
            }
		} finally {
			System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
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
		
		thread2.interrupt();//调用中断方法来测试能否中断等待中的线程
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
			System.out.println(Thread.currentThread().getName()+"被" + Thread.currentThread().getName() +"中断");
		}
	}
	
	@Override
	public void interrupt() {
		// TODO Auto-generated method stub
		super.interrupt();
		System.out.println(this.getName() + "111被" + Thread.currentThread().getName() +"中断");
	}
}

/*
Thread-0得到了锁
Thread-1被main中断
over!
Thread-1被Thread-1中断
 */
