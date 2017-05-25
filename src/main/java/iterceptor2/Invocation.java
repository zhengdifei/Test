package iterceptor2;

import java.util.ArrayList;
import java.util.List;


public class Invocation {

		List<Inteceptor> interceptors = new ArrayList<Inteceptor>();
		int index = -1;
		public Invocation() {
			//需要执行的拦截器
			this.interceptors.add(new FirstInterceptor());
			this.interceptors.add(new SecondInterceptor());
		}
		public void invoke() {
			index++;
			if (index < interceptors.size()) {
				//一次调用拦截器的inteceptor方法
				interceptors.get(index).inteceptor(this);
			} else {
				//拦截器拦截完毕后执行action
				new Action().execute();
			}
		}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new Invocation().invoke();
	}

}
