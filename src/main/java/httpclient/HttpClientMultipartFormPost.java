package httpclient;

import java.io.File;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * HttpClient 4处理文件上传的例子(MultipartEntity).<br>
 * 需要 James Mime4j 0.5的版本，0.6的不行。
 * 
 * @author JAVA世纪网(java2000.net, laozizhu.com)
 */
public class HttpClientMultipartFormPost {
  public static void main(String[] args) throws Exception {
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost("http://localhost");
    // 一个本地的文件
    FileBody bin = new FileBody(new File("d:/BIMG1181.JPG"));
    // 一个字符串
    StringBody comment = new StringBody("A binary file of some kind");
    // 多部分的实体
    MultipartEntity reqEntity = new MultipartEntity();
    // 增加
    reqEntity.addPart("bin", bin);
    reqEntity.addPart("comment", comment);
    // 设置
    httppost.setEntity(reqEntity);
    System.out.println("执行: " + httppost.getRequestLine());
    HttpResponse response = httpclient.execute(httppost);
    HttpEntity resEntity = response.getEntity();
    System.out.println("----------------------------------------");
    System.out.println(response.getStatusLine());
    if (resEntity != null) {
      System.out.println("返回长度: " + resEntity.getContentLength());
    }
    if (resEntity != null) {
      resEntity.consumeContent();
    }
  }
}