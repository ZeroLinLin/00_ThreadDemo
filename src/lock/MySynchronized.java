package lock;

public class MySynchronized {
	public static void main(String[] args) {
		final MySynchronized mySynchronized1 = new MySynchronized();
		final MySynchronized mySynchronized2 = new MySynchronized();
		
		new Thread("thread1") {
			@Override
			public void run() {
				synchronized (mySynchronized1) {
					System.out.println(this.getName() + "start");
					try {
						//int i = 1/0; //如果发生异常，jvm会将锁释放
						Thread.sleep(2000);
						System.out.println(this.getName() + "wakeup");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println(this.getName() + "end");
				}
			}
		}.start();
		
		new Thread("thread2") {
			@Override
			public void run() {
				synchronized (mySynchronized2) { //争抢同一把锁时，线程1没释放之前，线程2只能等待
					System.out.println(this.getName() + "start");
					
					System.out.println(this.getName() + "end");
				}
			}
		}.start();
		
		
	}
}
