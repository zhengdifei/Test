package scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 缓存线程池与固定线程池的区别在于对于需要执行很多短期异步任务的程序来说，
 * 缓存线程池可以提高程序性能，因为长时间保持空闲的这种类型的线程池不会占用任何资源，
 * 调用缓存线程池对象将重用以前构造的线程（线程可用状态），
 * 若线程没有可用的，则创建一个新线程添加到池中，缓存线程池将终止并从池中移除60秒未被使用的线程。 
 * 
 * @author Administrator
 *
 */
public class TestCahedThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int index = 0; index < 10; index++) {
			Runnable run = new Runnable() {
				public void run() {
					long time = (long) (Math.random() * 1000);
					System.out.println("sleep:" + time + " s ");
					try {
						Thread.sleep(time);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			};
			exec.execute(run);
		}
		exec.shutdown();
	}

}
