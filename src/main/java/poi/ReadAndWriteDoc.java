package poi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.FieldsDocumentPart;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Field;
import org.apache.poi.hwpf.usermodel.Fields;
import org.apache.poi.hwpf.usermodel.Range;

public class ReadAndWriteDoc {
	
	private URL base = this.getClass().getResource("");

	public void writeDoc(Map<String, String> map) {
		try {
			//读取word模板
			String fileDir = new File(base.getFile(),"../../../WebContent/doc/").getCanonicalPath();
			FileInputStream in = new FileInputStream(new File(fileDir+"/laokboke.doc"));
//			FileInputStream in = new FileInputStream("C://hello.doc");
			HWPFDocument hdt = new HWPFDocument(in);
			Fields fields = hdt.getFields();
			Iterator<Field> it = fields.getFields(FieldsDocumentPart.MAIN).iterator();
			while(it.hasNext()){
				System.out.println(it.next().getType());
			}
			
			int length = hdt.characterLength();
			for(int i = 0;i<length -1;i++){
				Range r = new Range(i,i+1,hdt);
				for(int j=0;j<r.numCharacterRuns();j++){
					CharacterRun cr = r.getCharacterRun(j);
					if(cr.getSubSuperScriptIndex() == 0){
						System.out.println(r.text());
					}
				}
			}
//			//读取word文本内容
//			Range range = hdt.getRange();
//			System.out.println(range.text());
//			//替换文本内容
//			for (Map.Entry<String,String> entry:map.entrySet()) {
//				range.replaceText(entry.getKey(),entry.getValue());
//			}    
			ByteArrayOutputStream ostream = new ByteArrayOutputStream();
			String fileName = ""+System.currentTimeMillis();
			fileName += ".doc";
			FileOutputStream out = new FileOutputStream(fileDir+"/"+fileName,true);
			hdt.write(ostream);
            //输出字节流
			out.write(ostream.toByteArray());
			out.close();
			ostream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		ReadAndWriteDoc rawDoc = new ReadAndWriteDoc();
		Map<String, String> map=new HashMap<String, String>();
		map.put("title", "老k博客");
		map.put("blog_name", "老k博客");
		map.put("domain_name", "laokboke.net");
		map.put("description", "是一个专注于wordpress、java、gis、建站、网站推广、seo的IT博客。");
		rawDoc.writeDoc(map);
	}

}
