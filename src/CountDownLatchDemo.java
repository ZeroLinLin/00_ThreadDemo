import java.util.Date;
import java.util.concurrent.CountDownLatch;
/*
CountDownLatch����ԭ����Լ򵥣����Լ򵥿���һ�������������ڹ��췽����ָ����ʼֵ��ÿ�ε���countDown()����ʱ����������1����await()��ȴ���������Ϊ0��CountDownLatch�ؼ��ӿ�����

countDown() �����ǰ��������ֵ����1�������1������ǰֵΪ1��������Ϊ0����������ͨ��await�ȴ����̣߳�����ǰֵΪ0����ʲôҲ����ֱ�ӷ��ء�
await() �ȴ���������ֵΪ0������������ֵΪ0��÷������أ����ȴ��ڼ���̱߳��жϣ����׳�InterruptedException��������̵߳��ж�״̬��
await(long timeout, TimeUnit unit) ��ָ����ʱ���ڵȴ���������ֵΪ0������ָ��ʱ���ڼ�������ֵ��Ϊ0����÷�������true����ָ��ʱ���ڼ�������ֵ��δ��Ϊ0���򷵻�false����ָ��ʱ���ڼ�������ֵ��Ϊ0֮ǰ��ǰ�̱߳��жϣ����׳�InterruptedException��������̵߳��ж�״̬��
getCount() ��ȡ��ǰ��������ֵ��һ�����ڵ��Ի��߲��ԡ�

һ�ֳ�������ĳ���߳���Ҫ�ȴ�һ�������̲߳�����������ﵽĳ��״̬���ſ�ʼִ�С�
���翪��һ���������Թ���ʱ�����߳���Ҫ�ȵ����в����߳̾�ִ������ٿ�ʼͳ���ܹ��ķѵ�ʱ�䣬��ʱ����ͨ��CountDownLatch����ʵ�֡�
 */
public class CountDownLatchDemo {
  public static void main(String[] args) throws InterruptedException {
    int totalThread = 3;
    long start = System.currentTimeMillis();
    CountDownLatch countDown = new CountDownLatch(totalThread);
    
    for(int i = 0; i < totalThread; i++) {
      final String threadName = "Thread " + i;
      
      new Thread(() -> {
        System.out.println(String.format("%s\t%s %s", new Date(), threadName, "started"));
        try {
          Thread.sleep(1000);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
        countDown.countDown();
        //System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
      }).start();;
      
    }
    
    countDown.await();
    long stop = System.currentTimeMillis();
    System.out.println(String.format("Total time : %sms", (stop - start)));
  }
}