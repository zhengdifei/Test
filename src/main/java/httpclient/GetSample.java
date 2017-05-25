package httpclient;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class GetSample{
  public static void main(String[] args) {
  //构造HttpClient的实例
  HttpClient httpClient = new HttpClient();
  //创建GET方法的实例
  GetMethod getMethod = new GetMethod("http://192.168.1.101:8080/EngineWorkflow/common.action?command=T_SYS_USERRESOURCE1.selectAll");
  //使用系统提供的默认的恢复策略
  getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
    new DefaultHttpMethodRetryHandler());
  try {
   //执行getMethod
   int statusCode = httpClient.executeMethod(getMethod);
   if (statusCode != HttpStatus.SC_OK) {
    System.err.println("Method failed: "
      + getMethod.getStatusLine());
   }
   //读取内容 
   byte[] responseBody = getMethod.getResponseBody();
   //处理内容
   System.out.println(new String(responseBody));
  } catch (HttpException e) {
   //发生致命的异常，可能是协议不对或者返回的内容有问题
   System.out.println("Please check your provided http address!");
   e.printStackTrace();
  } catch (IOException e) {
   //发生网络异常
   e.printStackTrace();
  } finally {
   //释放连接
   getMethod.releaseConnection();
  }
 }
}