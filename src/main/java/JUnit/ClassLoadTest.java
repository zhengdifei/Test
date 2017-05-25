package JUnit;

public class ClassLoadTest {
	//private static Test test;
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class cc = Class.forName("JUnit.Test");
		new Test();
		//Test test = null;//
		//new Test();
		//ClassLoadTest t = new ClassLoadTest();
	}

}
