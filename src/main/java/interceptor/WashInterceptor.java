package interceptor;

public class WashInterceptor implements Interceptor {
	
	@Override
	public String before() {
		return "Wash start!";
	}

	@Override
	public String after() {
		return "Washing";
	}

	@Override
	public String intercept(Invocation invocation) {
		before();
		invocation.execute();
		after();
		return null;
	}
}
