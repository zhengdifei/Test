package aop.Decorator;

import aop.base.SecurityChecker;

public class AccountWithSecurityCheck {
	private  Account account;
	
	public AccountWithSecurityCheck (Account account) {
		this.account = account;
	}
	public void operation() {
		SecurityChecker.checkSecurity();
		account.operation();
	}
}
