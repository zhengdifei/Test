package httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * HttpClient 4 使用POST方式提交普通表单数据的例子.
 * 
 * @author JAVA世纪网(java2000.net, laozizhu.com)
 */
public class HttpClientPost {
  public static void main(String[] args) throws Exception {
    DefaultHttpClient httpclient = new DefaultHttpClient();
    // 代理的设置
    HttpHost proxy = new HttpHost("10.60.8.20", 8080);
    httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
    // 目标地址
    HttpPost httppost = new HttpPost("http://www.java2000.net/login.jsp");
    System.out.println("请求: " + httppost.getRequestLine());
    // 构造最简单的字符串数据
    StringEntity reqEntity = new StringEntity("username=test&password=test");
    // 设置类型
    reqEntity.setContentType("application/x-www-form-urlencoded");
    // 设置请求的数据
    httppost.setEntity(reqEntity);
    // 执行
    HttpResponse response = httpclient.execute(httppost);
    HttpEntity entity = response.getEntity();
    System.out.println("----------------------------------------");
    System.out.println(response.getStatusLine());
    if (entity != null) {
      System.out.println("Response content length: " + entity.getContentLength());
    }
    // 显示结果
    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
    String line = null;
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
    }
    if (entity != null) {
      entity.consumeContent();
    }
  }
}