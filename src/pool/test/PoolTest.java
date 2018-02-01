package pool.test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PoolTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//������¼���̵߳ķ��ؽ��
		ArrayList<Future<?>> results = new ArrayList<Future<?>>();
		Random random = new Random();
		
		//�����̶������̳߳�
		/*ExecutorService exec = Executors.newFixedThreadPool(4);*/
		//���������̳߳�
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(4);
		
		Future<?> submit = null;
		for (int i = 0; i < 10; i++) {
			//fixedPool�ύ�̣߳�runnable�޷���ֵ��callable�з���ֵ
			/*submit = exec.submit(new TaskRunnable(i));*/
			/*submit = exec.submit(new TaskCallable(i));*/
			
			//����schedulerPool��˵������submit�ύ����ʱ������ͨpoolЧ��һ��
			/*submit = exec.submit(new TaskCallable(i));*/
			//����schedulerPool��˵������schedule�ύ����ʱ����ɰ��ӳ٣������ʱ��(ֻ����runnable)�������̵߳�����
			/*submit = exec.schedule(new TaskRunnable(i), random.nextInt(10), TimeUnit.SECONDS);*/
			submit = exec.scheduleAtFixedRate(new TaskRunnable(i), 1, 2, TimeUnit.SECONDS);
			
			results.add(submit);
		}
		
		//��ӡ���
		for(Future f: results){
			boolean done = f.isDone();
			System.out.println(done?"�����":"δ���");  //�ӽ���Ĵ�ӡ˳����Կ�������ʹδ��ɣ�Ҳ�������ȴ�
			System.out.println("�̷߳���future����� " + f.get());
		}
		
		exec.shutdown();
	}
}
