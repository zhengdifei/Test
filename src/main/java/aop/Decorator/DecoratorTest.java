package aop.Decorator;

public class DecoratorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AccountWithSecurityCheck awsc = new AccountWithSecurityCheck(new AccountImpl());
		awsc.operation();
	}
}
