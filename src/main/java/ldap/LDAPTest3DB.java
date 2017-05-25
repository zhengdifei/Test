package ldap;

import java.util.Hashtable;   

import javax.naming.Context;   
import javax.naming.NamingEnumeration;   
import javax.naming.NamingException;   
import javax.naming.directory.Attribute;   
import javax.naming.directory.Attributes;   
import javax.naming.directory.SearchControls;   
import javax.naming.directory.SearchResult;   
import javax.naming.ldap.InitialLdapContext;   
import javax.naming.ldap.LdapContext; 

public class LDAPTest3DB {
	public static void main(String[] args){
	    System.out.println(getPassword("abc@test.com"));   
	}   
	       
	       
	public static String getPassword(String user){   
		String password="";      
		Attribute pass=null;   
		String domain=user.split("@")[1];   
		//System.out.println(domain);   
		String tmp[]=domain.split("\\.");   
		StringBuffer dn1=new StringBuffer(""); //通过用户名，解析根后缀.   
		  
		for(int i=0;i<tmp.length;i++){ 
			if (i!=0) dn1.append(",");   
		       dn1=dn1.append("dc="+tmp[i]);   
		}       
		String dn=dn1.toString();   
		  
		        
		try{       
		    Hashtable env = new Hashtable();   
		    env.put(Context.PROVIDER_URL,"ldap://192.168.88.165:389");   
		    env.put(Context.SECURITY_AUTHENTICATION, "simple");   
		    env.put(Context.SECURITY_PRINCIPAL, "cn=Manager,dc=lizongbo,dc=com"); //用户名   
		    env.put(Context.SECURITY_CREDENTIALS, "123456"); //密码   
		    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");   
		    LdapContext ctx = new InitialLdapContext(env, null);   
		    SearchControls constraints = new SearchControls();   
		    constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);   
		    NamingEnumeration results = ctx.search(dn,"(mail="+user+")",constraints);    
		               
			while (results != null && results.hasMore()){   
				SearchResult sr = (SearchResult) results.next();   
				Attributes attributes = sr.getAttributes();   
			    pass = attributes.get("Password");   
			}
			
			password=pass.toString();   
			return password;
		}catch(Exception e){   
		    e.getStackTrace();
		    return null;
		}
	}   

}
