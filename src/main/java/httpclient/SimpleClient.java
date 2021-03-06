package httpclient;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class SimpleClient {
	public static void main(String[] args) throws IOException 
	{
	  HttpClient client = new HttpClient(); 
	      // 设置代理服务器地址和端口       

	      //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port); 
	      // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
	       HttpMethod method= new GetMethod("http://192.168.1.101:8080/EngineWorkflow/common.action?command=T_SYS_USERRESOURCE1.selectAll");
	      //使用POST方法
	      //HttpMethod method = new PostMethod("http://java.sun.com");
	      client.executeMethod(method);

	      //打印服务器返回的状态
	       System.out.println(method.getStatusLine());
	      //打印返回的信息
	      System.out.println(method.getResponseBodyAsString());
	      //释放连接
	      method.releaseConnection();
	 }
}