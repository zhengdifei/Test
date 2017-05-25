package threadTest;

public class ThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread ra = new Thread(new Runnable() {
			
			@Override
			public void run(){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("test1");
			}
		});
		
		ra.start();
		System.out.println("test2");
	}

}
