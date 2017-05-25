package aop.Proxy;

import java.lang.reflect.Proxy;

import aop.Decorator.Account;
import aop.Decorator.AccountImpl;

public class ProxyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Account account = (Account) Proxy.newProxyInstance(
				Account.class.getClassLoader(),
				new Class[] { Account.class },
				new SecurityProxyInvocationHandler(new AccountImpl())
		);
		
		Account account2 = (Account) Proxy.newProxyInstance(
				Account.class.getClassLoader(),
				new Class[] { Account.class },
				new SecurityProxyInvocationHandler2(account)
		);
		account2.operation();
	}

}
