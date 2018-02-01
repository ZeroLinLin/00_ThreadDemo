package lock.readwritelock;
/**
 * ����1�����������ж���߳�Ҫͬʱ���ж������Ļ����ȿ�һ��synchronized�ﵽ��Ч��
 * @author HY
 *
 */
public class MySynchronizedReadWrite {
	// ��ͨ������������Ķ���������ͬ��������ͬ
	public synchronized void operate(Thread thread) {
		long start = System.currentTimeMillis();
		int i = 0;
		while (System.currentTimeMillis() - start <= 1) {
			i++;
			if (i % 4 == 0) {
				System.out.println(thread.getName() + "���ڽ���д����");
			} else {
				System.out.println(thread.getName() + "���ڽ��ж�����");
			}
		}
		System.out.println(thread.getName() + "��д�������");
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
