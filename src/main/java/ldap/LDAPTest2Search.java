package ldap;

import com.novell.ldap.*;

public class LDAPTest2Search {
	/**
	 * 1.建立LDAP连接
	 * 2.LDAP查询
	 * 3.查询结果遍历
	 * 4.输出查询结果
	 * 5.关闭连接
	 * @param args
	 */
	 public static void main(String[] args) {
		 LDAPConnection lc = new LDAPConnection();    
	       try {    
	           lc.connect("192.168.88.211",389);//ip,端口号
	           lc.bind(LDAPConnection.LDAP_V3,"cn=ldap,dc=dsideal,dc=com","123456"); //协议，用户名，密码   
	           LDAPSearchResults rs = lc.search("dc=dsideal,dc=com",LDAPConnection.SCOPE_SUB,"(|(id=ttttt1)(email=smith))",null,false);    
	           int count = 0;
	           //遍历查询节点
	           while(rs.hasMore()){ 
	               LDAPEntry entry = rs.next();    
	               System.out.println(entry.getDN());
	               count++;    
	           }    
	           System.out.println("共有"+count+"条记录。");
	           lc.disconnect();
	       } catch (LDAPException e) {
	           System.err.print("连接异常！   ");
	           e.printStackTrace();    
	       }   

	 }
}
