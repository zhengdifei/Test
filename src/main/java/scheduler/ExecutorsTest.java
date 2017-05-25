package scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Executors类位于java.util.concurrent包下，提供了一些方便构建ThreadPoolExecutor和线程管理的方法。
 * http://blog.csdn.net/java2000_wl/article/details/22333257
 * @author Administrator
 *
 */
public class ExecutorsTest {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		//schedule方法
		ScheduledFuture<String> sf = ses.schedule(new Callable<String>(){

			@Override
			public String call() throws Exception {
				return "call";
			}
			
		}, 10, TimeUnit.SECONDS);
		
		System.out.println(sf.get());
		ses.shutdown();
		
		//scheduleAtFixedRate方法
		/*
		 * command：执行的任务 Callable或Runnable接口实现类
		 * initialDelay：第一次执行任务延迟时间
		 * period：连续执行任务之间的周期，从上一个任务开始执行时计算延迟多少开始执行下一个任务，但是还会等上一个任务结束之后。
		 * unit：initialDelay和period时间单位
		 */
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ses = Executors.newScheduledThreadPool(1);
		ses.scheduleAtFixedRate(new Runnable(){

			@Override
			public void run() {
				System.out.println("时间：" + sdf.format(new Date()));
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}, 2, 3, TimeUnit.SECONDS);
		ses.shutdown();
		
		//scheduleWithFixedDelay
		/*
		 * command：执行的任务 Callable或Runnable接口实现类
		 * initialDelay：第一次执行任务延迟时间
		 * period：连续执行任务之间的周期，从上一个任务全部执行完成时计算延迟多少开始执行下一个任务
		 * unit：initialDelay和period时间单位
		 */
		ses = Executors.newScheduledThreadPool(1);
		ses.scheduleWithFixedDelay(new Runnable(){

			@Override
			public void run() {
				System.out.println("时间：" + sdf.format(new Date()));
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}, 2, 3, TimeUnit.SECONDS);
		//ses.shutdown();
		
	}

}
