package threeimpl;

import java.util.Random;

public class MyThreadWithImplimentRunnable implements Runnable {
	private String flag;
	
	public MyThreadWithImplimentRunnable(String flag) {
		super();
		this.flag = flag;
	}

	@Override
	public void run() {
		// 获取当前线程的线程名
		String tname = Thread.currentThread().getName();
		System.out.println(tname + "线程的run方法被调用");
		Random random = new Random();
		
		for (int i=0; i<20; i++) {
			try {
				Thread.sleep(random.nextInt(10)*100);
				System.out.println(tname + "***" + flag);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		MyThreadWithImplimentRunnable myThreadWithImplimentRunnable = new MyThreadWithImplimentRunnable("a");
		Thread thread1 = new Thread(myThreadWithImplimentRunnable);
		Thread thread2 = new Thread(myThreadWithImplimentRunnable);
		thread1.start();
		thread2.start();
	}
}
