package aop.asm;

import java.io.IOException;

import aop.base.Account;

public class AsmTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassFormatError 
	 */
	public static void main(String[] args) throws ClassFormatError, InstantiationException, IllegalAccessException, IOException {
		//改变Account的operation方法
		System.out.println("改变Account的operation方法:");
		Account account = new Account();
		account.operation();
		
		System.out.println("");
		System.out.println("将动态生成类改造成原始类 Account 的子类:");
		//将动态生成类改造成原始类 Account 的子类
		Account account2 = SecureAccountGenerator.generateSecureAccount();
		account2.operation();
		
		System.out.println("");
		System.out.println("测试是否通过子类拦截：");
		//测试是否通过子类拦截
		Account account3 = new Account();
		account3.operation();
		
		try {
			System.out.println("4");
			Account a4 = (Account) Class.forName("aop.base.Account$EnhancedByASM").newInstance();
			a4.operation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
