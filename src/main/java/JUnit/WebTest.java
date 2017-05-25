package JUnit;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

public class WebTest {
	
	/**
	 * 测试URL是否有返回response。可用来测试URL地址存在和服务是否存在等。
	 */
	@Test
	public void testWebExists(){
		//核心类，用来模拟一个浏览器的操作，相当于拥有了一个浏览器
		WebConversation web = new WebConversation();
			try {
				//使用URL字符串地址作为参数，测试服务是否开启。
				//geResponse获得返回的response
				web.getResponse("http://www.google.com.hk");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}

	}
	/**
	 * 使用WebRequest作为参数测试服务是否开启。
	 */
	@Test
	public void testWebExists2(){
		WebConversation web = new WebConversation();
		//使用GET方法创建request对象
		WebRequest request = new GetMethodWebRequest("http://www.baidu.com");
		try {
			web.getResponse(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试response中的内容
	 */
	@Test
	public void testWebContent(){
		WebConversation web = new WebConversation();
		//使用GET方法创建request对象
		WebRequest request = new GetMethodWebRequest("http://www.google.com.hk");
		try {
			WebResponse response = web.getResponse(request);
			//显示response中的html内容
			System.out.println(response.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试带参数的request
	 */
	@Test
	public void testWebContentWithParamenter(){
		WebConversation web = new WebConversation();
		WebRequest request = new GetMethodWebRequest("http://www.google.com.hk");
		//使用reuqest的setParameter方法可以为生成的request添加参数
		request.setParameter("username", "yuchujin");
		try {
			WebResponse response = web.getResponse(request);
			System.out.println(response.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试页面的跳转
	 */
	@Test
	public void testWebRedirectSuccess(){
		WebConversation web = new WebConversation();
		//生成POST的request
		WebRequest request = new PostMethodWebRequest("http://localhost:8080/HttpUnitProject/validate.jsp");
		//使用reuqest的setParameter方法可以为生成的request添加参数
		request.setParameter("username", "ycj");
		request.setParameter("password", "123");
		try {
			WebResponse response = web.getResponse(request);
			String expectedURL = "http://localhost:8080/HttpUnitProject/success.jsp";
			Assert.assertEquals("validate fail",expectedURL, response.getURL().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试页面跳转失败
	 */
	@Test
	public void testWebRedirectFail(){
		WebConversation web = new WebConversation();
		WebRequest request = new PostMethodWebRequest("http://localhost:8080/HttpUnitProject/validate.jsp");
		//使用reuqest的setParameter方法可以为生成的request添加参数
		request.setParameter("username", "ycj");
		request.setParameter("password", "321");
		try {
			WebResponse response = web.getResponse(request);
			String expectedURL = "http://localhost:8080/HttpUnitProject/fail.jsp";
			Assert.assertEquals("validate fail",expectedURL, response.getURL().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试获得页面中的table节点
	 */
	@Test
	public void testWebTables(){
		WebConversation web = new WebConversation();
		WebRequest request = new GetMethodWebRequest("http://localhost:8080/HttpUnitProject/table.jsp");
		WebResponse response = null;
		try {
			response = web.getResponse(request);
			//WebTable代表返回的html中的table标签封装的对象
			//response.getTables返回html中所有table dom对象数组
			WebTable tables = response.getTables()[0];
			Assert.assertEquals(2, tables.getColumnCount());
			Assert.assertEquals(3, tables.getRowCount());
			//获得表格中某个单元格中的文本
			Assert.assertEquals("male", tables.getTableCell(2, 1));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试超链接内容
	 */
	@Test
	public void testWebLink(){
		WebConversation web = new WebConversation();
		WebRequest request  = new GetMethodWebRequest("http://localhost:8080/HttpUnitProject/linkFrom.jsp");
		try {
			WebResponse response = web.getResponse(request);
			//模拟html中的超链接，在页面中通过超连接的文本获得超连接的URL
			WebLink link = response.getLinkWith("click me to linkTo");
			//将超链接生成request
			WebRequest linkReq = link.getRequest();
			//访问刚超链接页面
			response = web.getResponse(linkReq);
			Assert.assertEquals("http://localhost:8080/HttpUnitProject/linkTo.jsp",response.getURL().toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试form中的内容
	 */
	@Test
	public void testWebForm(){
		WebConversation web = new WebConversation();
		WebRequest request = new GetMethodWebRequest("http://localhost:8080/HttpUnitProject/form.jsp");
		WebResponse response = null;
		try {
			response = web.getResponse(request);
			//获得Html中的form表单，HttpUnit将他封装成WebForm对象
			WebForm form = response.getForms()[0];
			//WebForm对象提供getParameterValue的方法将根据表单中的name获得对应的value,而不用管该元素的类型。
			Assert.assertEquals("yuchujin", form.getParameterValue("name"));
			Assert.assertEquals("mela", form.getParameterValue("sex"));
			Assert.assertEquals("high", form.getParameterValue("school"));
			Assert.assertEquals("on", form.getParameterValue("cj"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试WebForm的处理表单和提交能力
	 */
	@Test
	public void testFormSubmit(){
		WebConversation web = new WebConversation();
		WebRequest request = new PostMethodWebRequest("http://localhost:8080/HttpUnitProject/form.jsp");
		WebResponse response = null;
		try {
			response = web.getResponse(request);
			//获得Html中的form表单，HttpUnit将他封装成WebForm对象
			WebForm form = response.getForms()[0];
			//WebForm对象提供getParameterValue的方法将根据表单中的name获得对应的value,而不用管该元素的类型。
			Assert.assertEquals("yuchujin", form.getParameterValue("name"));
			Assert.assertEquals("mela", form.getParameterValue("sex"));
			Assert.assertEquals("high", form.getParameterValue("school"));
			Assert.assertEquals("on", form.getParameterValue("cj"));
			//对表单进行处理操作(根据name)
			form.setParameter("name", "zhoujianzhen");
			//有的元素不能够remove，比如说radio。限制只发现checkbox能删，能研究一下再修改文章。
			form.removeParameter("school");
			//提交表单 获得新的response
			response = form.submit();
			Assert.assertEquals("http://localhost:8080/HttpUnitProject/handleForm.jsp", response.getURL().toString());
			System.out.println(response.getText());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
}