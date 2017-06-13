package doc;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

/**
 * Created  on 2017/5/24 0024.
 * @author zhengdifei
 * @desc word操作集合
 */
public class DocUtil {
    //日志
    private static final Logger log = LoggerFactory.getLogger(DocUtil.class);

    /**
     *  Aspose 加载license文件，没有license，导出文件有水印
     */
    static{
        InputStream is = DocTestByAspose.class.getClassLoader().getResourceAsStream("license.xml");
        License asposeLic = new License();
        try {
            asposeLic.setLicense(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @desc 通过freemarker 生成doc文件
     * @param datas  模板数据，${key}格式
     * @param templateFileName 模板文件名称，模板文件位于class相对路径
     * @param exportFile 输出文件路径
     */
    public static void createDocByFreeMarker(Map<String,Object> datas, String templateFileName, String exportFile){
        Configuration configure = new Configuration(Configuration.VERSION_2_3_22);
        configure.setDefaultEncoding("utf-8");
        configure.setClassForTemplateLoading(DocUtil.class,"");
        configure.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

        try {
            Template template  = configure.getTemplate(templateFileName);
            File outFile = new File(exportFile);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
            template.process(datas,out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * @desc 通过Aspose生成doc文件
     * @param datas 模板数据，${key}格式
     * @param importFile 输入文件，doc格式
     * @param exportFilePath 输出文件路径
     * @param exportFileName 输出文件名称，doc格式
     */
    public static void createDocByAspose(Map<String,Object> datas,String importFile, String exportFilePath, String exportFileName ){
        createFileByAspose(datas,importFile,exportFilePath + exportFileName + ".doc",SaveFormat.DOC);
    }

    /**
     * @desc 通过Aspose生成docx文件
     * @param datas 模板数据，${key}格式
     * @param importFile 输入文件，doc格式
     * @param exportFilePath 输出文件路径
     * @param exportFileName 输出文件名称，docx格式
     */
    public static void createDocxByAspose(Map<String,Object> datas,String importFile, String exportFilePath, String exportFileName ){
        createFileByAspose(datas,importFile,exportFilePath + exportFileName + ".docx",SaveFormat.DOCX);
    }

    /**
     * @desc 通过Aspose生成docx文件
     * @param datas 模板数据，${key}格式
     * @param importFile 输入文件，doc格式
     * @param exportFilePath 输出文件路径
     * @param exportFileName 输出文件名称，pdf格式
     */
    public static void createPdfByAspose(Map<String,Object> datas,String importFile, String exportFilePath, String exportFileName ){
        createFileByAspose(datas,importFile,exportFilePath + exportFileName + ".pdf",SaveFormat.PDF);
    }

    /**
     * @desc 通过Aspose生成pdf文件
     * @param datas 模板数据，${key}格式
     * @param importFile 输入文件，doc格式
     * @param exportFile 输出文件
     * @param fileMode 输出文件格式
     */
    public static void createFileByAspose(Map<String,Object> datas,String importFile, String exportFile ,int fileMode){
        try {
            Document doc = new Document(importFile);
            Iterator<String> keys = datas.keySet().iterator();
            while(keys.hasNext()){
                String key = keys.next();
                if( datas.get(key) instanceof String){
                    String value = String.valueOf(datas.get(key));

                    value = value.replace("\r\n","");

                    doc.getRange().replace("${" + key + "}",value,true,false);
                }else if( datas.get(key).getClass().isArray() && datas.get(key) instanceof byte[]){
                    DocumentBuilder builder = new DocumentBuilder(doc);
                    builder.moveToBookmark(key);
                    builder.insertImage((byte[]) datas.get(key));
                }else{
                    System.out.println("待处理");
                }

            }

            doc.save(exportFile,fileMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @desc 将一个图片生成base64数据
     * @param imgFile 图片文件路径
     * @return
     */
    public static String getImageStr(String imgFile){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(getImage(imgFile));
    }

    /**
     * @desc 将一个图片生成字节数组
     * @param imgFile 图片文件路径
     * @return
     */
    public static byte[] getImage(String imgFile){
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
