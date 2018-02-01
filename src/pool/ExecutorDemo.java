package pool;
/**
 * 列出并发包中的各种线程池
 * @author HY
 *
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorDemo {
	public static void main(String[] args) {
		// 创建单线程线程池
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		
		//创建可回收的线程池
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		
		//创建固定数量线程池
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		
		// 创建可调度的线程池
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(8);
		// 创建可调度的单线程 线程池	
		ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
	}
}
