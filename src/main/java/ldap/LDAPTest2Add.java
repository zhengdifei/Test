package ldap;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

public class LDAPTest2Add {
	/**
	 * 1.连接LDAP连接
	 * 2.定义属性
	 * 3.构建LDAP对象（objectClass）
	 * 4.将对象插入到LDAP服务器。
	 * 5.关闭连接
	 * @param args
	 */
	 public static void main(String[] args) {
		 LDAPConnection lconn = new LDAPConnection();    
	       try {    
	    	   //连接
	    	   lconn.connect("192.168.88.211",389);    
	           //绑定
	    	   lconn.bind(LDAPConnection.LDAP_V3,"cn=ldap,dc=dsideal,dc=com","123456");  
	           //定义属性
	           LDAPAttributeSet attributeSet = new LDAPAttributeSet();    
	           attributeSet.add(new LDAPAttribute("objectClass", new String("STUDENT")));    
	           attributeSet.add(new LDAPAttribute("id", new String[] {"tt2"}));    
	           attributeSet.add(new LDAPAttribute("email", new String("Smith")));    
	           //定义实体
	           LDAPEntry entry = new LDAPEntry("id=tt2,dc=dsideal,dc=com",attributeSet);    
	           //添加一条记录
	           lconn.add(entry);    
	           System.out.println("成功的添加了一条记录！"); 
	           //关闭连接
	           lconn.disconnect();   

	       } catch (LDAPException e) {    
	              
	           System.err.print("连接异常！   ");    
	           e.printStackTrace();    
	       }   

	 }
}