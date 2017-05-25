package threadTest.simple;


public class SimpleThread  extends Thread{

	/**
	 * @param args
	 */
	//private static Test monitor = new Test();
	private static int ThreadCount =0;
	private int val = 0;
	private int Countdown = 5;
	
	public SimpleThread(int i){
		super(""+ ++ThreadCount);
		val =i;
		start();
	}
	
	public String toString(){
		return "#"+val+"     $$$$: sdffsdafsdf  sdfsadf  sdfsadfsadfsadfsad fsdf   "+Countdown;
	}
	
	public void run(){
		while(true){
			System.out.println(this);
			
			if(--Countdown==0) return;
			//yield();
			//try {
			//	wait(100);
			//} catch (InterruptedException e) {
			//	e.printStackTrace();
			//}
			
			try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0 ;i<5;i++)
			new SimpleThread(i);	
	}

}
