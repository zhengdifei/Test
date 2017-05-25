package chart;

import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
public class JFreeChartBarTest {
    public static void main(String[] args){
        //创建主题样式
        StandardChartTheme sct = new StandardChartTheme("CN");
        //设置标题字体
        sct.setExtraLargeFont(new Font("隶书", Font.BOLD,20));
        //设置图例的字体
        sct.setRegularFont(new Font("宋书",Font.PLAIN,15));
        //设置轴向的字体
        sct.setLargeFont(new Font("宋书",Font.PLAIN,15));

        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        dcd.setValue(10,"a","管理人员");
        dcd.setValue(20,"b","市场人员");
        dcd.setValue(40,"c","开发人员");
        dcd.setValue(15,"d","其他人员");

        ChartFactory.setChartTheme(sct);
        //可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL
        JFreeChart chart = ChartFactory.createBarChart("人员数量表","人员分布","人员数量",dcd, PlotOrientation.VERTICAL,true,true,false);
        chart.setTitle(new TextTitle("公司组织结构图",new Font("宋体",Font.BOLD,20)));
        CategoryPlot plot = (CategoryPlot) chart.getPlot();//获得图标中间部分，即plot
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));

        ChartFrame chartFrame = new ChartFrame("人员表",chart);
        //以合适的大小展现图形
        chartFrame.pack();
        //图形是否可见
        chartFrame.setVisible(true);

        OutputStream os = null;
        try {
            os = new FileOutputStream("d:\\company.jpeg");
            //使用一个面向application的工具类，将chart转换成JPEG格式的图片。第3个参数是宽度，第4个参数是高度。
            ChartUtilities.writeChartAsJPEG(os,chart,1000,800);
            os.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
