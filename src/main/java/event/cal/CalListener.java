package event.cal;

import java.util.EventListener;

public interface CalListener extends EventListener {    //事件接口类

    public boolean StepProformed(CalEvent event);     //申明单步运算函数接口

    public void CalOverProformed(CalEvent event);     //申明计算完成函数接口

    public void CancelProformed(CalEvent event);      //申明计算取消函数接口

}