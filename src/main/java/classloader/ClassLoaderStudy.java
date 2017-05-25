package classloader;

public class ClassLoaderStudy {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws  Exception {
//		          Class clazz = new MyClassLoader().loadClass("MyClassTest");  
//		          Method meth = clazz.getMethod("sayHello"); 
//		          String result = (String) meth.invoke(clazz.newInstance(), null);
//		          System.out.println(result);
				//Class clazz = new MyClassLoader().loadClass("MyClassTest"); 
		        Class clazz = new MyClassLoader().loadClass("MyClassTest");
				clazz.newInstance();
		        System.out.println("tt");
  		    }  

	}
 