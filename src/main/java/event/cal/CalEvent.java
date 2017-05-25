package event.cal;

import java.util.EventObject;

public class CalEvent extends EventObject {              //计算工具事件类

    public static final int STEPOVER=1;           //申明事件标志，单步计算完成

    public static final int CALOVER=2;            //计算完成标志

    public static final int CALCANCEL=3;         //计算取消标志

    public static final int TYPEERROR=-1;

 
    protected long StepNum;                    //单步计算的步数

    protected int Type;                         //事件类型

 
    public CalEvent(Object src) {                //几个事件构造函数

        super(src);                           //登记事件源

        Type=TYPEERROR;

    }

    public CalEvent(Object src, int mask) {

        super(src);

        if(mask==STEPOVER || mask==CALOVER || mask==CALCANCEL) Type=mask;

        else Type=TYPEERROR;

    }

    public CalEvent(Object src, int mask, long step) {

        super(src);

        if(mask==STEPOVER || mask==CALOVER || mask==CALCANCEL) Type=mask;

        else Type=TYPEERROR;

        StepNum=step;

}

//一些实用方法

    public void setType(int mask) { Type=mask; }

    public void setType(int mask, long step){Type=mask; StepNum=step;}

    public int getType() { return Type; }

    public long getStepNum() { return StepNum; }

    public boolean isStepCal() {

        if(Type==CalEvent.STEPOVER) return true;

        else return false;

    }

    public boolean isCalOver() {

        if(Type==CalEvent.CALOVER) return true;

        else return false;

    }

    public boolean isCalCancel() {

        if(Type==CalEvent.CALCANCEL) return true;

        else return false;

    }

}