package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object proxyObj;
	
	public MyInvocationHandler(Object obj){
		proxyObj = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(method.getName().equals("call")){
			method.invoke(proxyObj, args);		
			System.out.println("method invoke");
		}
		return null;
	}

}