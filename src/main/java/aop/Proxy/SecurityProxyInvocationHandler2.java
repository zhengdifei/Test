package aop.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import aop.Decorator.Account;
import aop.base.SecurityChecker;

public class SecurityProxyInvocationHandler2 implements InvocationHandler{

	private Object proxyedObject;
	
	public SecurityProxyInvocationHandler2(Object o) {
		proxyedObject = o;
	}

	public Object invoke(Object object, Method method, Object[] arguments)
		throws Throwable {			
		System.out.println("开始2");
		Object result = method.invoke(proxyedObject, arguments);
		System.out.println("结束2");
		return result;
	}
}
