package jmx;

/**
 * 首先，我们并不想让远程管理者能够关闭我们的中央热水器，因为热水器一旦关上，我们再也无法访问厂家提供的API。
 * 既然不能关闭它，我们的MBeans中也就不需要打开(turnOn)方法。
 * 
 * @author carlwu
 * 
 */

public interface CentralHeaterImplMBean {

	/**
	 * return the heater provider
	 * 
	 * @return
	 */
	public String getHeaterProvider();

	/**
	 * Get current temperature of heater
	 * 
	 * @return the temperature of the heater
	 */
	public int getCurrentTemperature();

	/**
	 * Set the new temperature
	 * 
	 * @param newTemperature
	 */
	public void setCurrentTemperature(int newTemperature);

	/**
	 * Print the current temperature of the heater
	 * 
	 * @return the string of current temperature
	 */
	public String printCurrentTemperature();
	
	public String testName1();

}