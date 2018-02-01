package lock.readwritelock;
/**
 * 例子1：　　假如有多个线程要同时进行读操作的话，先看一下synchronized达到的效果
 * @author HY
 *
 */
public class MySynchronizedReadWrite {
	// 普通方法，锁是类的对象，声明不同对象锁不同
	public synchronized void operate(Thread thread) {
		long start = System.currentTimeMillis();
		int i = 0;
		while (System.currentTimeMillis() - start <= 1) {
			i++;
			if (i % 4 == 0) {
				System.out.println(thread.getName() + "正在进行写操作");
			} else {
				System.out.println(thread.getName() + "正在进行读操作");
			}
		}
		System.out.println(thread.getName() + "读写操作完毕");
	}
	
	public static void main(String[] args) {
		final MySynchronizedReadWrite test = new MySynchronizedReadWrite();
		final MySynchronizedReadWrite test2 = new MySynchronizedReadWrite();
		new Thread() {
			public void run() {
				test.operate(Thread.currentThread());
			};
		}.start();
		
		new Thread() {
			public void run() {
				//test.operate(Thread.currentThread());
				test2.operate(Thread.currentThread());
			};
		}.start();
	}
}
