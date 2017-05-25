package event.cal;

public class CalTst {                            //测试应用程序，即具体的监听者

    public static void main(String[] arg) {

        System.out.println("Test Begin:");

        myCal1 c1=new myCal1();               //申明计算工具1的实例

        c1.addCalListener(new myCal1Adapter());  //注册工具1的监听者

        c1.Run();                             //运行工具1

        myCal2 c2=new myCal2();               //申明工具2的实例

        c2.addCalListener(new myAdaper());       //注册工具2监听者

        c2.Run();                              //运行工具2

    }

}
