package jmx;
/**
 * 我们更改已有的CentralHeaterImpl.java代码。
 * 但从代码编写的角度看，这种做法违反了软件设计的基本原则—开闭原则。我们已有的CentralHeaterImpl.java类已经经过多次测试，消除了所有的Bug，
 * 现在为了支持JMX，我又增加方法，又修改代码，这会让原本运行得很好的系统重新产生Bug。
 * 您不妨思考一下，如何不修改CentralHeaterImpl类的代码，但又让使JMX能够管理我们家的热水器呢？
 * 
 * @author Administrator
 *
 */
public class CentralHeaterImpl implements CentralHeaterInf,
		CentralHeaterImplMBean {

	int currentTemperature;

	public int getCurrentTemperature() {
		return currentTemperature;
	}

	public void setCurrentTemperature(int newTemperature) {
		currentTemperature = newTemperature;
	}

	public void turnOff() {
		System.out.println("The heater is off. ");
	}

	public void turnOn() {
		System.out.println("The heater is on. ");

	}

	public String getHeaterProvider() {
		return HEATER_PROVIDER;

	}

	public String printCurrentTemperature() {

		String printMsg = "Current temperature is:" + currentTemperature;
		System.out.println(printMsg);
		return printMsg;
	}

	@Override
	public String testName1() {
		return "zdf1";
	}

	@Override
	public 	String testName2() {
		return "zdf2";
	}

}