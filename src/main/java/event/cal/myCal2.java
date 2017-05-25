package event.cal;

public class myCal2 extends Calculation {            //计算工具2类

    public void Run() {                     //计算工具2运行

        notifyEvent(CalEvent.CALOVER);    //产生计算完成事件

    }

}