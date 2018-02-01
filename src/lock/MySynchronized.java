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
						//int i = 1/0; //��������쳣��jvm�Ὣ���ͷ�
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
				synchronized (mySynchronized2) { //����ͬһ����ʱ���߳�1û�ͷ�֮ǰ���߳�2ֻ�ܵȴ�
					System.out.println(this.getName() + "start");
					
					System.out.println(this.getName() + "end");
				}
			}
		}.start();
		
		
	}
}
