package interceptor;

public class WarnupInterceptor implements Interceptor {
	@Override
	public String before() {
		return "Warn Up!";
	}

	@Override
	public String after() {
		return "Warn Up finish!";
	}

	@Override
	public String intercept(Invocation invocation){
		before();
		invocation.execute();
		after();
		return null;
	}
}
