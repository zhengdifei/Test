package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class MainClass {

	/**
	 * @param args
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		InvocationHandler handler = new MyInvocationHandler(new Foo());
		IFoo f = (IFoo) Proxy.newProxyInstance(Foo.class.getClassLoader(),new Class[] { Foo.class.getSuperclass() },handler);
	     
	    f.call();
	}

}
