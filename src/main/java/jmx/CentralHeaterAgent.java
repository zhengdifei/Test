package jmx;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * 中央热水系统(Central Heater System)
 * 
 * JMX要管理的对象是什么呢，是资源。
 * 什么是资源，资源是指企业中的的各种应用软件和平台，举例来说，一个公司内部可能有许多应用服务器、若干Web服务器、一台至多台的数据库服务器及文件服务器等等，
 * 那么，如果我们想监视数据库服务器的内存使用情况，或者我们想更改应用服务器上JDBC最大连接池的数目，
 * 但我们又不想重启数据库和应用服务器，这就是典型意义上的资源管理，
 * 即对我们的资源进行监视(Monitoring，查看)和管理(Management，更改)，这种监视和更改不妨碍当前资源的正常运行。
 * 
 * @author carlwu
 * 
 */
public class CentralHeaterAgent {
	private static MBeanServer mBeanServer;

	/**
	 * 
	 * 代理层中最重要的对象就是MBeanServer，我们可以把MBeanServer理解为一个全局的HashMap，
	 * 所有的MBeans都通过唯一的名字注册到这个HashMap，
	 * 这个HashMap可以跨越JVM访问，甚至可以通过RMI、Http及其它手段跨越网络传输到其它机器，
	 * 让其它机器也能访问这个MBeanServer中注册的对象。
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		ObjectName oname;
		//a)首先我们从ManagementFactory的工厂方法中获得MBeanServer对象
		mBeanServer = ManagementFactory.getPlatformMBeanServer();
		// try {
		//b)然后实例化我们的热水器对象，注意这个对象声明为CentralHeaterInf，而不是CentralHeaterImplMBean。
		//JMX规范并没有规定对象声明，只要这个对象实现了一个以SomethingMBean命名的接口或父类即可；
		CentralHeaterInf centralHeater = new CentralHeaterImpl();
		
		CentralHeaterInf centralHeater2 = new CentralHeaterImpl();

		//c)接下来通过new ObjectName(String)构造函数给我们的MBean一个全局的名字，
		//这个名字一般的格式是：”域名:属性1=*,属性2=*,…”构成；
		oname = new ObjectName("MyHome:name=centralheater");
		//d)第四步，我们调用MBeanServer的regiesterBean方法，
		//通过第三步声明的全局名字把我们的MBean实例注册到MBeanServer。
		mBeanServer.registerMBean(centralHeater, oname);
		
		oname = new ObjectName("MyHome:name=centralheater2");
		mBeanServer.registerMBean(centralHeater2, oname);
		
		System.out.println("Press any key to end our JMX agent...");
		System.in.read();

	}

}