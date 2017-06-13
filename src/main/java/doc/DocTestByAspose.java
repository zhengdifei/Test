package doc;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.aspose.words.net.System.Data.DataRow;
import com.aspose.words.net.System.Data.DataSet;
import com.aspose.words.net.System.Data.DataTable;
import com.aspose.words.net.System.Data.DataView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26 0026.
 */
public class DocTestByAspose {

    private static final Logger log = LoggerFactory.getLogger(DocTestByAspose.class);

    public static void main(String[] args) {
        Map<String, Object> dataMap=new HashMap<String, Object>();
        dataMap.put("name", "Joanna");
        dataMap.put("examNum", "111111111111");
        dataMap.put("IDCard", "222222222222222222");
        dataMap.put("carModel", "C1");
        dataMap.put("drivingSchool", "测试驾校");
        dataMap.put("busyType", "初次申领");
        dataMap.put("examDate", "2016-03-10");
        dataMap.put("orderCount", "第1次");
        dataMap.put("userImg", DocUtil.getImage("D:\\zdf.png"));
        dataMap.put("firstExamTime", "12:41:17-12:44:38");
        dataMap.put("firstExamScores", "0分，不及格");
        dataMap.put("firstDeductItem", "12:44:15 20102 1号倒车入库，车身出线 扣100分");
        dataMap.put("secondExamTime", "12:46:50-13:05:37");
        dataMap.put("secondExamScores", "90分，通过");
        dataMap.put("secondDeductItem", "");

        try {
            Document doc = new Document("d:\\templateAspose.docx");

            doc.getMailMerge().execute(new MapMailMergeDataSource(dataMap,"test"));
            //doc.getMailMerge().execute(new String[]{"name","userImg"},new Object[]{"郑而",DocUtil.getImage("D:\\zdf.png")});

            doc.save("d:\\t9.doc");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //DocUtil.createDocByAspose(dataMap,"d:\\template.xml", "d:\\","t4");
        //DocUtil.createDocxByAspose(dataMap,"d:\\template.xml", "d:\\","t5");
        //DocUtil.createPdfByAspose(dataMap,"d:\\template.xml", "d:\\","t6");
        //DocUtil.createFileByAspose(dataMap,"d:\\template.xml","d:\\t7.png",SaveFormat.PNG);
    }
}