package poi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.FieldsDocumentPart;
import org.apache.poi.hwpf.usermodel.Field;
import org.apache.poi.hwpf.usermodel.Fields;
import org.apache.poi.hwpf.usermodel.Range;

public class ReadWriteAndDownloadDocServlet extends HttpServlet{
	private URL base = this.getClass().getResource("");
	
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		try {
			
			//读取word模板文件
			String fileDir = new File(base.getFile(),"../../../doc/").getCanonicalPath();
			FileInputStream in = new FileInputStream(new File(fileDir+"/laokboke.doc"));
			HWPFDocument hdt = new HWPFDocument(in);
			Fields fields = hdt.getFields();
			Iterator<Field> it = fields.getFields(FieldsDocumentPart.MAIN).iterator();
			while(it.hasNext()){
				System.out.println(it.next().getType());
			}
			
			//替换读取到的word模板内容的指定字段
			Range range = hdt.getRange();
			Map<String, String> map=new HashMap<String, String>();
			map.put("title", "老k博客");
			map.put("blog_name", "老k博客");
			map.put("domain_name", "laokboke.net");
			map.put("description", "是一个专注于wordpress、java、gis、建站、网站推广、seo的IT博客。");
			for (Map.Entry<String,String> entry:map.entrySet()) {
				range.replaceText(entry.getKey(),entry.getValue());
			}
			
			//输出word内容文件流，提供下载
			response.reset();
            response.setContentType("application/x-msdownload");
            response.addHeader("Content-Disposition", "attachment; filename=\"laokboke.doc\"");
			ByteArrayOutputStream ostream = new ByteArrayOutputStream();
			ServletOutputStream servletOS = response.getOutputStream();
			hdt.write(ostream);
			servletOS.write(ostream.toByteArray());
			servletOS.flush();
			servletOS.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}

}
