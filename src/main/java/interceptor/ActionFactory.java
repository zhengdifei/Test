package interceptor;

import java.util.ArrayList;
import java.util.List;

public class ActionFactory implements Invocation{
	public List<Interceptor> interceptors = new ArrayList<Interceptor>();
	
	private Action action;
	
	private int offset = -1;
	
	public void addInterceptor(Interceptor interceptor){
		interceptors.add(interceptor);
	}
	
	public void addAction(Action action){
		this.action = action;
	}
	
	public String execute(){
		offset++;
		
		if(offset<interceptors.size()){
			interceptors.get(offset).intercept(this);
			
		}else{
			action.execute();
		}
		
		return null;
	}
	
	public static void main(String[] args){
		ActionFactory af = new ActionFactory();
		
		af.addInterceptor(new WarnupInterceptor());
		af.addInterceptor(new WashInterceptor());
		
		af.addAction(new SportAction());
		
		af.execute();
	}
}
