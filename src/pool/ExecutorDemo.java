package pool;
/**
 * �г��������еĸ����̳߳�
 * @author HY
 *
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorDemo {
	public static void main(String[] args) {
		// �������߳��̳߳�
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		
		//�����ɻ��յ��̳߳�
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		
		//�����̶������̳߳�
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		
		// �����ɵ��ȵ��̳߳�
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(8);
		// �����ɵ��ȵĵ��߳� �̳߳�	
		ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
	}
}
