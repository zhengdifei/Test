package classloader;

public class IMyClass {
	
	public IMyClass(){
		System.out.println("IMyClass:" + IMyClass.class.getClassLoader().toString());
	}
	
	public String sayHello(){
		return null;
	}
}
