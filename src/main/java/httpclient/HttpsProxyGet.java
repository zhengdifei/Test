package httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * HttpClient 4.0通过代理访问Https的代码例子。
 * 
 * @author JAVA世纪网(java2000.net, laozizhu.com)
 */
public class HttpsProxyGet {
  public static void main(String[] args) throws Exception {
    DefaultHttpClient httpclient = new DefaultHttpClient();
    // 认证的数据
    // 我这里是瞎写的，请根据实际情况填写
    httpclient.getCredentialsProvider().setCredentials(new AuthScope("10.60.8.20", 8080),
        new UsernamePasswordCredentials("username", "password"));
    // 访问的目标站点，端口和协议
    HttpHost targetHost = new HttpHost("www.google.com", 443, "https");
    // 代理的设置
    HttpHost proxy = new HttpHost("10.60.8.20", 8080);
    httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
    // 目标地址
    HttpGet httpget = new HttpGet("/adsense/login/zh_CN/?");
    System.out.println("目标: " + targetHost);
    System.out.println("请求: " + httpget.getRequestLine());
    System.out.println("代理: " + proxy);
    // 执行
    HttpResponse response = httpclient.execute(targetHost, httpget);
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