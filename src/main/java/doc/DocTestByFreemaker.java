package doc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
public class DocTestByFreemaker {
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
        dataMap.put("userImg1", DocUtil.getImageStr("D:\\zdf.png"));
        dataMap.put("firstExamTime", "12:41:17-12:44:38");
        dataMap.put("firstExamScores", "0分，不及格");
        dataMap.put("firstDeductItem", "12:44:15 20102 1号倒车入库，车身出线 扣100分");
        dataMap.put("secondExamTime", "12:46:50-13:05:37");
        dataMap.put("secondExamScores", "90分，通过");
        dataMap.put("secondDeductItem", "");
        DocUtil.createDocByFreeMarker(dataMap, "template.xml", "D:\\t3.doc");
    }
}
