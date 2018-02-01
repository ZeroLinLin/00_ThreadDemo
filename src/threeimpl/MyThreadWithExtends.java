package threeimpl;

import java.util.Random;

public class MyThreadWithExtends extends Thread {
	private String flag;
	
	public MyThreadWithExtends(String flag) {
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
		MyThreadWithExtends thread1 = new MyThreadWithExtends("a");
		MyThreadWithExtends thread2 = new MyThreadWithExtends("b");
		
		thread1.start();
		thread2.start();
	}
}
