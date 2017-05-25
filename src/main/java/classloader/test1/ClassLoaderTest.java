package classloader.test1;

public class ClassLoaderTest {
	public static void main(String[] args) {
	       Singleton singleton=Singleton.getInstance();
	       System.out.println("counter1:"+singleton.getCounter1());
	       System.out.println("counter2:"+singleton.getCounter2());
	    }
}
