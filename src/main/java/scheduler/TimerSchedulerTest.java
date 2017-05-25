package scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerSchedulerTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date startDate = sdf.parse("2010/11/28 01:06:00");
		Timer timer = new Timer();
		//schedule用法，执行时间从2016开始，可以看出，间隔时间都为6秒，因此，下一次的执行时间点=上一次程序执行完成的时间点+间隔时间
//		timer.schedule(new TimerTask(){
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			
//			@Override
//			public void run() {
//				try {
//					//当<5*1000,执行间隔是5*1000
//					Thread.sleep(4000);
//					//当>5*1000,执行间隔是6000
//					//Thread.sleep(6000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println("execute task!"+ sdf.format(new Date(this.scheduledExecutionTime())));  
//			}
//			
//		}, startDate,5*1000);
		
		//scheduleAtFixedRate用法，执行时间从2010开始，因此，下一次的执行时间点=上一次程序开始执行的时间点+间隔时间 ；并且因为前一个任务要执行6秒，而当前任务已经开始执行了，因此两个任务间存在重叠，需要考虑线程同步
		timer.scheduleAtFixedRate(new TimerTask(){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			@Override
			public void run() {
				try {
					//执行间隔不受此处影响
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("execute task!"+ sdf.format(new Date(this.scheduledExecutionTime())));  
			}
			
		}, startDate,5*1000);
	}

}
