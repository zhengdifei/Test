package threadTest;

public class Consumer implements Runnable 
{
	StackBasket ss = new StackBasket(); 
    Consumer(StackBasket ss){ 
        this.ss = ss; 
    } 
 
    /** 
    * show 消费进程.
    */  
    public void run(){ 
        for(int i = 0;i < 20;i++){ 
            Mantou m = ss.pop(); 
//          System.out.println("消费了：---------" + m + " 共" + ss.index + "个馒头"); 
//  同上  在上面一行进行测试也是不妥的，对index的访问应该在原子操作里，因为可能在pop之后此输出之前又生产了，会产生输出混乱 
            try{ 
                Thread.sleep((int)(Math.random()*1000)); 
            }catch(InterruptedException e){ 
                e.printStackTrace(); 
            } 
        } 
    }
}
