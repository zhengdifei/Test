package TCP.setTcpNoDelay;

public class CtrlC implements Runnable {   
    public static boolean bExit = false;   
    
    public CtrlC() {   
        
    }   
    public void run() {   
    	int i=0;
        while (!bExit) {   
            // Do some thing   
        	System.out.print("\r"+(++i));
        }   
        System.out.println("Exit OK");   
    }   
    public static void main(String[] args) throws InterruptedException {   
    	Runtime.getRuntime().addShutdownHook(new ExitHandler());   
        CtrlC ctrlc = new CtrlC();   
        Thread t = new Thread(ctrlc);   
        t.setName("Ctrl C Thread");   
        t.run();   
        t.join();   
//    	int i=0;
//        while(CtrlC.bExit){
//        	System.out.print("\r"+(++i));
//        }
        System.out.println("\n---------------------------");
    }   
}  

class ExitHandler extends Thread {   
    public ExitHandler() {   
        super("Exit Handler");   
    }   
    public void run() {   
//        System.out.println("Set exit");   
        CtrlC.bExit = true;   
    }   
}   