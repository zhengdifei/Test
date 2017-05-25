package threadTest;

public class Producer implements Runnable 
{
	StackBasket ss = new StackBasket(); 
    Producer(StackBasket ss){ 
        this.ss = ss; 
    } 
 
    /** 
    * show 生产进程. 
    */  
    public void run(){ 
        for(int i = 0;i < 20;i++){ 
            Mantou m = new Mantou(i); 
            ss.push(m); 
//          System.out.println("生产了：" + m + " 共" + ss.index + "个馒头"); 
//          在上面一行进行测试是不妥的，对index的访问应该在原子操作里，因为可能在push之后此输出之前又消费了，会产生输出混乱 
            try{ 
                Thread.sleep((int)(Math.random()*500)); 
            }catch(InterruptedException e){ 
                e.printStackTrace(); 
            } 
        } 
    }
}
