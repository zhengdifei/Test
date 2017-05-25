package threadTest.simple;

public class SimpleDaemons extends Thread{

	/**
	 * @param args
	 */
	public SimpleDaemons(){
		setDaemon(true);
		start();
	}
	
	public void run(){
		while(true){
			System.out.println(this);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e); 
			}
			System.out.println(this);
		}
	}
	public static void main(String[] args) {
			for(int i=0 ;i<10;i++)
				new SimpleDaemons();
	}

}
