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
		System.out.println(tname + " call����������");
		return this.acceptStr + tname + " **** call��������ֵ";
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CallableImpl callable = new CallableImpl("my callable");
		FutureTask<String> task = new FutureTask<>(callable);
		
		long beginTime = System.currentTimeMillis();
		new Thread(task).start();
		
		// ����get()���������̣߳���֮�����̲߳�������
		String result = task.get();
		long endTime = System.currentTimeMillis();

		System.out.println(result);
		System.out.println("cast : " + (endTime - beginTime) / 1000 + " second!");
	}
}
