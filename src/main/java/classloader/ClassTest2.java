package classloader;

public class ClassTest2 {
	
	public static String showName(){
		String str = "t2 name";
		System.out.println(str);
		return str;
	}
	
	public ClassTest2(){
		System.out.println("ClassTest2:" + ClassTest2.class.getClassLoader().toString());
		System.out.println("ClassTest2 parant:" +ClassTest2.class.getClassLoader().getParent().toString());
	}
}
