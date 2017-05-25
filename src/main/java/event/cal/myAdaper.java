package event.cal;

public class myAdaper extends CalAdapter {          //计算工具2适配器

    public void CalOverProformed(CalEvent e) {//计算完成处理函数

        System.out.println("The Source is "+e.getSource().toString());

    }

}
