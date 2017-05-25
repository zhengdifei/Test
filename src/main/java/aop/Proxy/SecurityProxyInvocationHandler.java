package aop.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import aop.Decorator.Account;
import aop.base.SecurityChecker;

public class SecurityProxyInvocationHandler implements InvocationHandler{

	private Object proxyedObject;
	
	public SecurityProxyInvocationHandler(Object o) {
		proxyedObject = o;
	}

	public Object invoke(Object object, Method method, Object[] arguments)
		throws Throwable {			
//		if (object instanceof Account && method.getName().equals("operation")) {
//			SecurityChecker.checkSecurity();
//		}
		System.out.println("开始");
		Object result = method.invoke(proxyedObject, arguments);
		System.out.println("结束");
		return result;
	}
}
