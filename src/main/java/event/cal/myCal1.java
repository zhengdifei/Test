package event.cal;

public class myCal1 extends Calculation {               //计算工具1类

    public void Run() {                        //运行工具1方法

        for(int i=0; i<5; i++) notifyEvent(CalEvent.STEPOVER,i);//产生单步事件

        notifyEvent(CalEvent.CALOVER);       //产生计算完成事件

    }

    public String getResult(){ return "OK"; }       //返回计算完成结果

}