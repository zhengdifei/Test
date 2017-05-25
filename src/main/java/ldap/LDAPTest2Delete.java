package ldap;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

public class LDAPTest2Delete {
	/**
	 * 1.建立LDAP连接
	 * 2.根据节点ID，删除一个节点。
	 * 3.关闭连接
	 * @param args
	 */
	public static void main(String[] args) {
		 LDAPConnection lc = new LDAPConnection();    
	       try {
	    	   //连接
	           lc.connect("192.168.88.211",389);
	           lc.bind(LDAPConnection.LDAP_V3,"cn=Manager,dc=guessant,dc=org","secret");    
	           //删除
	           lc.delete("cn=dd,dc=guessant,dc=org");    
	           System.out.println("成功删除一条记录！"); 
	           //关闭连接
	           lc.disconnect();
	       } catch (LDAPException e) {
	           System.err.print("连接异常！   ");
	           e.printStackTrace();    
	       }   

	 }
}
