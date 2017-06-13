package doc.openOffice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class ConverterTest {

    private static Logger logger = LoggerFactory.getLogger(ConverterTest.class);

    /**
     * 1. 安装OpenOffice 3 下载路径：http://zh.openoffice.org/new/zh_cn/downloads.html 2. 启动OpenOffice服务 cd
     * C:\Program Files\OpenOffice.org 3\program soffice -headless
     * -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard 3. 利用Jodconverter编写转换类
     */
    public static void convert(String input) {
        convert(input, getOutputFilePath(input));
    }

    /**
     * 1. 安装OpenOffice 3 下载路径：http://zh.openoffice.org/new/zh_cn/downloads.html 2. 启动OpenOffice服务 cd
     * C:\Program Files\OpenOffice.org 3\program soffice -headless
     * -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard 3. 利用Jodconverter编写转换类
     */
    public static void convert(String input, String output) {
        File inputFile = new File(input);
        File outputFile = new File(output);
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.disconnect();
                    connection = null;
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * 在项目中启动OpenOffice
     */
    public static int office2PDF(String sourceFile) {
        return office2PDF(sourceFile, getOutputFilePath(sourceFile));
    }

    /**
     * 在项目中启动OpenOffice
     */
    public static int office2PDF(String sourceFile, String destFile) {
        String OpenOffice_HOME = getOfficeHome();// 这里是OpenOffice的安装目录,
        // 在我的项目中,为了便于拓展接口,没有直接写成这个样子,但是这样是尽对没题目的
        // 假如从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'
        if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '/') {
            OpenOffice_HOME += "/";
        }
        Process pro = null;
        OpenOfficeConnection connection = null;
        try {
            // 启动OpenOffice的服务
            String command =
                    OpenOffice_HOME
                            + "program/soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
            pro = Runtime.getRuntime().exec(command);
            // connect to an OpenOffice.org instance running on port 8100
            connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
            connection.connect();

            // convert
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            File inputFile = new File(sourceFile);
            File outputFile = new File(destFile);
            converter.convert(inputFile, outputFile);

            return 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close the connection
            connection.disconnect();
            // 封闭OpenOffice服务的进程
            pro.destroy();
        }

        return 1;
    }

    public static String getOutputFilePath(String inputFilePath) {
        String outputFilePath = inputFilePath.replaceAll(".docx?", ".pdf");
        return outputFilePath;
    }

    public static String getOfficeHome() {
        String osName = System.getProperty("os.name");
        logger.info("osName: " + osName);
        if (Pattern.matches("Linux.*", osName)) {
            return "/opt/openoffice.org3";
        } else if (Pattern.matches("Windows.*", osName)) {
            return "D:\\Program Files (x86)\\OpenOffice 4";
        } else if (Pattern.matches("Mac.*", osName)) {
            return "/Application/OpenOffice.org.app/Contents";
        }
        return null;
    }

    public static void main(String[] args) {
        String currPath = CurrPathUtil.getCurProgramPath();
        String inputFile = currPath + "files" + File.separator + "contract_template.odt";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String fn = sdf.format(new Date());
        String outputFile = currPath + "files" + File.separator + "Converte_" + fn + ".pdf";
        System.out.println(ConverterTest.office2PDF(inputFile, outputFile));
    }
}