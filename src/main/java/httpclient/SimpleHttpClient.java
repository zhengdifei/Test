package httpclient;

import java.io.IOException; 
import org.apache.commons.httpclient.*; 
import org.apache.commons.httpclient.methods.*; 

/** 
 *提交参数演示
 *该程序连接到一个用于查询手机号码所属地的页面
 *以便查询号码段1330227所在的省份以及城市
 *@authorLiudong
 */
public class SimpleHttpClient { 
   public static void main(String[] args) throws IOException {
      HttpClient client = new HttpClient();
      client.getHostConfiguration().setHost( "192.168.1.101" , 8080, "http" );
      HttpMethod method = getPostMethod();//getPostMethod();    // 使用 POST 方式提交数据 
      client.executeMethod(method);   //打印服务器返回的状态 
      System.out.println(method.getStatusLine());   //打印结果页面
      String response=new String(method.getResponseBodyAsString());

      //打印返回的信息
      System.out.println(response);
      method.releaseConnection();
   }
   
   /** 
    * 使用 GET 方式提交数据 
    *@return 
    */

   private static HttpMethod getGetMethod(){
      return new GetMethod("/EngineWorkflow/common.action?command=T_SYS_USERRESOURCE.selectAll");
   }

    /** 
     * 使用 POST 方式提交数据 
     *@return 
     */
    private static HttpMethod getPostMethod(){
      PostMethod post = new PostMethod( "/EngineWorkflow/common.action" );
      NameValuePair simcard = new NameValuePair( "command" , "T_SYS_USERRESOURCE.selectAll" );
      post.setRequestBody( new NameValuePair[] { simcard});
      return post;
   } 
} 
