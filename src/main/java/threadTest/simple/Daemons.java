package threadTest.simple;

class Daemon extends Thread{
	private Thread[] t = new Thread[10];
	
	public Daemon(){
		setDaemon(true);
		start();
	}
	
	public void run(){
		for(int i=0;i<t.length;i++)
				t[i]= new DaemonSpawn(i);
		for(int i=0;i<t.length;i++)
				System.out.println("t["+i+"].isDaemon()="+t[i].isDaemon());
		while(true)
				yield();
	}
}

class DaemonSpawn extends Thread{
	public DaemonSpawn(int i){
		start();
		System.out.println("Daemons "+i+"  started.");
	}
	
	public void run(){
		while(true)
				yield();
	}
}
public class Daemons extends Thread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread d = new Daemon();
		System.out.println("d.isDaemon()="+d.isDaemon());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
