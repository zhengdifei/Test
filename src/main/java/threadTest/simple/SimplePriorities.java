package threadTest.simple;

public class SimplePriorities extends Thread{

	private int Countdown = 5;
	private  volatile double d =0;
	public SimplePriorities(int i){
		setPriority(i);
		start();
	}
	
	public String toString(){
		return "#"+super.toString()+"     $$$$: sdffsdafsdf  sdfsadf  sdfsadfsadfsadfsad fsdf   "+Countdown;
	}
	
	public void run(){
		while(true){
			for(int i=1;i<50000;i++){
				d=d+(Math.PI+Math.E)/(double)100;
			}
			System.out.println(this);
			
			if(--Countdown==0) return;
			
		}
	}
	public static void main(String[] args) {
		new SimplePriorities(Thread.MAX_PRIORITY);
		for(int i = 0 ;i<5;i++)
			new SimplePriorities(Thread.MIN_PRIORITY);	
	}

}
