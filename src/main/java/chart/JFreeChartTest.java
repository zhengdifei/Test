package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
public class JFreeChartTest {
    public static void main(String[] args){
        //创建主题样式
        StandardChartTheme sct = new StandardChartTheme("CN");
        //设置标题字体
        sct.setExtraLargeFont(new Font("隶书", Font.BOLD,20));
        //设置图例的字体
        sct.setRegularFont(new Font("宋书",Font.PLAIN,15));
        //设置轴向的字体
        sct.setLargeFont(new Font("宋书",Font.PLAIN,15));

        DefaultPieDataset dpd = new DefaultPieDataset();
        dpd.setValue("管理人员",25);
        dpd.setValue("市场人员",25);
        dpd.setValue("开发人员",40);
        dpd.setValue("其他人员",10);

        ChartFactory.setChartTheme(sct);
        //可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL
        JFreeChart chart = ChartFactory.createPieChart("人员数量表",dpd,true,true,false);

        ChartFrame chartFrame = new ChartFrame("人员表",chart);
        //以合适的大小展现图形
        chartFrame.pack();
        //图形是否可见
        chartFrame.setVisible(true);
    }
}
