package doc.openOffice;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import org.artofsolving.jodconverter.OfficeDocumentConverter;
//import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
//import org.artofsolving.jodconverter.office.OfficeManager;


/**
 *  SourceFrom : http://blog.csdn.net/yali1990515/article/details/7771835
 */

public class Openoffice2Pdf {
    /**
     * @param args
     */
//    private static OfficeManager officeManager;// 需要导入一个
//    private static String OFFICE_HOME = "D:\\Program Files (x86)\\OpenOffice 4\\";// 安装OPenOffice 的路径
//    private static int port[] = {8100};
//
//    public void convert2PDF(String inputFile, String outputFile) throws FileNotFoundException {
//        // 开启服务器
//        startService();
//        // 进行转换
//        System.out.println("进行文档转换转换:" + inputFile + " --> " + outputFile);
//        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
//        converter.convert(new File(inputFile), new File(outputFile));
//        // 关闭服务器
//        stopService();
//        System.out.println();
//    }
//
//    // 打开服务器
//    public static void startService() {
//        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
//        try {
//            System.out.println("准备启动服务....");
//            configuration.setOfficeHome(OFFICE_HOME);// 设置OpenOffice.org安装目录
//            configuration.setPortNumbers(port); // 设置转换端口，默认为8100
//            configuration.setTaskExecutionTimeout(1000 * 60 * 5L);// 设置任务执行超时为5分钟
//            configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);// 设置任务队列超时为24小时
//
//            officeManager = configuration.buildOfficeManager();
//            officeManager.start(); // 启动服务
//            System.out.println("office转换服务启动成功!");
//        } catch (Exception ce) {
//            System.out.println("office转换服务启动失败!详细信息:" + ce);
//        }
//    }
//
//    // 关闭服务器
//    public static void stopService() {
//        System.out.println("关闭office转换服务....");
//        if (officeManager != null) {
//            officeManager.stop();
//        }
//        System.out.println("关闭office转换服务成功!");
//    }
//
//    // 进行测试转换是否成功
//    public static void main(String[] args) {
//    /*String currPath = CurrPathUtil.getCurProgramPath();
//    String inputFile = currPath + "files" + File.separator + "Replace_20151223112323.odt";
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
//    String fn = sdf.format(new Date());
//    String outputFile = currPath + "files" + File.separator + "Converte_" + fn + ".pdf";
//    Openoffice2Pdf opc = new Openoffice2Pdf();
//    try {
//      opc.convert2PDF(inputFile, outputFile);
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    }*/
//
//        startService();
//    }

}