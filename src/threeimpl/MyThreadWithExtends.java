package threeimpl;

import java.util.Random;

public class MyThreadWithExtends extends Thread {
	private String flag;
	
	public MyThreadWithExtends(String flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		// ��ȡ��ǰ�̵߳��߳���
		String tname = Thread.currentThread().getName();
		System.out.println(tname + "�̵߳�run����������");
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
