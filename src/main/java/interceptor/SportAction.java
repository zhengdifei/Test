package interceptor;

public class SportAction implements Action {
	
	@Override
	public String execute() {
		System.out.println("do sporting!");
		return null;
	}
}
