package threadTest;

public class StackBasket {
	Mantou sm[] = new Mantou[6]; 
    int index = 0; 
     
    /** 
    * show 生产方法.
    * show 该方法为同步方法，持有方法锁；
    * show 首先循环判断满否，满的话使该线程等待，释放同步方法锁，允许消费；
    * show 当不满时首先唤醒正在等待的消费方法，但是也只能让其进入就绪状态，
    * show 等生产结束释放同步方法锁后消费才能持有该锁进行消费
    * @param m 元素
    * @return 没有返回值 
    */  
 
    public synchronized void push(Mantou m){ 
        try{ 
            while(index == sm.length){ 
                System.out.println("!!!!!!!!!生产满了!!!!!!!!!"); 
                this.wait(); 
            } 
            this.notify(); 
        }catch(InterruptedException e){ 
            e.printStackTrace(); 
        }catch(IllegalMonitorStateException e){ 
            e.printStackTrace(); 
        } 
         
        sm[index] = m; 
        index++; 
        System.out.println("生产了：" + m + " 共" + index + "个馒头"); 
    } 
 
    /** 
    * show 消费方法
    * show 该方法为同步方法，持有方法锁
    * show 首先循环判断空否，空的话使该线程等待，释放同步方法锁，允许生产；
    * show 当不空时首先唤醒正在等待的生产方法，但是也只能让其进入就绪状态
    * show 等消费结束释放同步方法锁后生产才能持有该锁进行生产
    * @param b true 表示显示，false 表示隐藏 
    * @return 没有返回值 
    */  
    public synchronized Mantou pop(){ 
        try{ 
            while(index == 0){ 
                System.out.println("!!!!!!!!!消费光了!!!!!!!!!"); 
                this.wait(); 
            } 
            this.notify(); 
        }catch(InterruptedException e){ 
            e.printStackTrace(); 
        }catch(IllegalMonitorStateException e){ 
            e.printStackTrace(); 
        } 
        index--; 
        System.out.println("消费了：---------" + sm[index] + " 共" + index + "个馒头"); 
        return sm[index]; 
    }
}
