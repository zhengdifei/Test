package event.cal;

public class myCal1Adapter extends CalAdapter {      //计算工具1适配器

    public boolean StepProformed(CalEvent e) { //单步事件处理函数

        System.out.println("Step "+e.getStepNum());

        return true;

    }

    public void CalOverProformed(CalEvent e) { //计算完成处理函数

        System.out.println("The Result is "+((myCal1)e.getSource()).getResult());

    }

}