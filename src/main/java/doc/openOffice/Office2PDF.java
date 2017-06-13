package doc.openOffice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 将Office文档转换为PDF文档
 *
 */
public class Office2PDF {

    private static Logger logger = LoggerFactory.getLogger(Office2PDF.class);

    /**
     * 环境变量下面的url.properties的绝对路径
     */
    private static final String RUL_PATH = Thread.currentThread().getContextClassLoader()
            .getResource("").getPath().replace("%20", " ")
            + "url.properties";

    /**
     * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice, OpenOffice下载地址为 http://www.openoffice.org/
     *
     * <pre>
     * 方法示例:
     * String sourcePath = "F:\\office\\source.doc";
     * String destFile = "F:\\pdf\\dest.pdf";
     * Converter.office2PDF(sourcePath, destFile);
     * </pre>
     *
     * @param sourceFile 源文件, 绝对路径. 可以是Office2003-2007全部格式的文档, Office2010的没测试. 包括.doc, .docx, .xls,
     *        .xlsx, .ppt, .pptx等. 示例: F:\\office\\source.doc
     * @param destFile 目标文件. 绝对路径. 示例: F:\\pdf\\dest.pdf
     * @return 操作成功与否的提示信息. 如果返回 -1, 表示找不到源文件, 或url.properties配置错误; 如果返回 0, 则表示操作成功; 返回1, 则表示转换失败
     */
    public static int office2PDF(String sourceFile, String destFile) {
        FileInputStream fis = null;
        OpenOfficeConnection connection = null;
        Process pro = null;
        try {
            File inputFile = new File(sourceFile);
            if (!inputFile.exists()) {
                return -1;// 找不到源文件, 则返回-1
            }

            // 如果目标路径不存在, 则新建该路径
            File outputFile = new File(destFile);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }

      /*
       * 从url.properties文件中读取OpenOffice的安装根目录, OpenOffice_HOME对应的键值. 我的OpenOffice是安装在D:\Program
       * Files\OpenOffice.org 3下面的, 如果大家的 OpenOffice不是安装的这个目录下面，需要修改url.properties文件中的
       * OpenOffice_HOME的键值. 但是需要注意的是：要用"\\"代替"\",用"\:"代替":" . 如果大家嫌麻烦,
       * 可以直接给OpenOffice_HOME变量赋值为自己OpenOffice的安装目录
       */
            Properties prop = new Properties();

            fis = new FileInputStream(RUL_PATH);// 属性文件输入流
            prop.load(fis);// 将属性文件流装载到Properties对象中


            String OpenOffice_HOME = prop.getProperty("OpenOffice_HOME_Window");
            logger.info("OpenOffice_HOME_Window: " + OpenOffice_HOME);
            if (OpenOffice_HOME == null)
                return -1;
            // 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'
            if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {
                OpenOffice_HOME += "\\";
            }
            // 启动OpenOffice的服务
            String command =
                    OpenOffice_HOME
                            + "program\\soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";
            pro = Runtime.getRuntime().exec(command);
            // connect to an OpenOffice.org instance running on port 8100
            String url = prop.getProperty("local_url");
            int port = Integer.valueOf(prop.getProperty("local_port")).intValue();
            connection = new SocketOpenOfficeConnection(url, port);
            connection.connect();

            // convert
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);
            return 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // close the connection
            connection.disconnect();
            // 关闭OpenOffice服务的进程
            pro.destroy();
        }
        return 1;
    }

    public static void main(String[] args) {
        String currPath = CurrPathUtil.getCurProgramPath();
        String inputFile = currPath + "files" + File.separator + "contract_template.odt";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String fn = sdf.format(new Date());
        String outputFile = currPath + "files" + File.separator + "Converte_" + fn + ".pdf";
        System.out.println(office2PDF(inputFile, outputFile));
    }
}
