package interceptor;

public interface Interceptor {
	
	public String intercept(Invocation invocation);
	public String before();
	public String after();
}
