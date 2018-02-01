package threeimpl.callablevsrunnable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableImpl implements Callable<String> {
	private String acceptStr;
	
	public CallableImpl(String acceptStr) {
		super();
		this.acceptStr = acceptStr;
	}


	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		String tname = Thread.currentThread().getName();
		System.out.println(tname + " call方法被调用");
		return this.acceptStr + tname + " **** call方法返回值";
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CallableImpl callable = new CallableImpl("my callable");
		FutureTask<String> task = new FutureTask<>(callable);
		
		long beginTime = System.currentTimeMillis();
		new Thread(task).start();
		
		// 调用get()会阻塞主线程，反之，主线程不会阻塞
		String result = task.get();
		long endTime = System.currentTimeMillis();

		System.out.println(result);
		System.out.println("cast : " + (endTime - beginTime) / 1000 + " second!");
	}
}
