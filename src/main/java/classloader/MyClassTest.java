package classloader;

public class MyClassTest extends IMyClass {
	
	public ClassTest2 ct2 = new ClassTest2();
	
	protected String str = ClassTest2.showName();
	
	public MyClassTest(){
		System.out.println("MyClassTest:" + MyClassTest.class.getClassLoader().toString());
		System.out.println("MyClassTest parant:" +MyClassTest.class.getClassLoader().getParent().toString());

	}
	
	public String sayHello() {
		return "Hello !";
	}
}
