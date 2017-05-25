package event.cal;

import java.util.Vector;

public class Calculation {                                //计算类，实际的计算事件源

    protected Vector listener;                       //监听者队列

    public Calculation(){ listener=new Vector(); }

    public synchronized void addCalListener(CalListener l) { listener.add(l); }//添加监听者

    public synchronized void removeCalListener(CalListener l){listener.remove(l);}

    public boolean notifyEvent(int mask) {            //事件通知函数重载版本1

        Vector l=new Vector();

        l=(Vector)listener.clone();

        boolean rt=true;

        CalEvent ev=new CalEvent(this);

        if(mask==CalEvent.STEPOVER) ev.setType(CalEvent.STEPOVER,0);

        else ev.setType(mask);

        synchronized(this) {                      //多线程锁定

            for(int i=0; i<l.size(); i++) {            //查询监听者，并调用处理函数

                if(ev.isStepCal()) rt=((CalListener)l.elementAt(i)).StepProformed(ev);

                else if(ev.isCalOver()) ((CalListener)l.elementAt(i)).CalOverProformed(ev);

                else if(ev.isCalCancel()) ((CalListener)l.elementAt(i)).CancelProformed(ev);

            }

        }

        return rt;

    }

    public boolean notifyEvent(int mask, long step) {  //事件通知函数重载版本2

        Vector l=new Vector();

        l=(Vector)listener.clone();

        boolean rt=true;

        CalEvent ev=new CalEvent(this);

        if(mask==CalEvent.STEPOVER) ev.setType(CalEvent.STEPOVER,step);

        else ev.setType(mask);

        synchronized(this) {

            for(int i=0; i<l.size(); i++) {

                if(ev.isStepCal()) rt=((CalListener)l.elementAt(i)).StepProformed(ev);

                else if(ev.isCalOver()) ((CalListener)l.elementAt(i)).CalOverProformed(ev);

                else if(ev.isCalCancel()) ((CalListener)l.elementAt(i)).CancelProformed(ev);

            }

        }

        return rt;

    }

}