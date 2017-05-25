package event.cal;

public class CalAdapter implements CalListener {            //事件适配器类，实现3个空处理函数

    public boolean StepProformed(CalEvent event){return true;}

    public void CalOverProformed(CalEvent event){}

    public void CancelProformed(CalEvent event){}

}