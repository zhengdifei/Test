package doc;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by Administrator on 2017/5/26 0026.
 */
public class Doc2TestByAspose {

    private static final Logger log = LoggerFactory.getLogger(Doc2TestByAspose.class);

    public static void main(String[] args) {
        try {
            List<Map<String,Object>> dataset = new ArrayList<Map<String,Object>>();

            byte[] image = DocUtil.getImage("d:\\zdf.png");

            for (int i = 0; i < 20 ; i++) {
                Map<String,Object> record = new HashMap<String,Object>();
                record.put("FirstName", "欧阳");
                record.put("LastName", "夏丹");
                record.put("Title", "个人简历导出Word PDF");
                record.put("Address", "中国 北京市 东城区");
                record.put("City", "北京");
                record.put("Country", "辽宁沈阳");
                //二进制数据
                record.put("PhotoBLOB", image);
                dataset.add(record);
            }

            Document doc = new Document("d:\\EmployeesReportDemo.doc");

            doc.getMailMerge().executeWithRegions(new MapMailMergeDataSource(dataset,"Employees"));

            doc.save("d:\\t8.doc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
