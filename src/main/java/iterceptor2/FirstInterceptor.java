package iterceptor2;

public class FirstInterceptor implements Inteceptor {

	
	public void inteceptor(Invocation invocation) {
		
		System.out.println("第一个拦截器开始。。。。");  
		//调用invoke方法 
		invocation.invoke() ;  
		System.out.println("第一个拦截器结束.......");
	}

}
