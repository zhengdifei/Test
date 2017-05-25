package annotation;

@Person(name="zdf",age=25)
public class AnnoTest {
	
	public static void print(Class c){
		System.out.println(c.getName());
		
		//java.lang.Class的getAnnotation方法，如果有注解，则返回注解。否则返回null
		Person p = (Person)c.getAnnotation(Person.class);
		
		if(p != null){
			System.out.println("name:"+p.name()+" age:"+p.age());
		}else{
			 System.out.println("person unknown!");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnnoTest.print(AnnoTest.class);
	}

}
