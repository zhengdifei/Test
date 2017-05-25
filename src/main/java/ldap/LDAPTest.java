package ldap;

import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class LDAPTest {

	public LDAPTest() {
	  }
	 
	  public static void main(String[] args) {
	    LDAPTest LDAPTest1 = new LDAPTest();
	    String root = "dc=dsideal,dc=com"; //root
	 
	    Hashtable env = new Hashtable();
	    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	    env.put(Context.PROVIDER_URL, "ldap://192.168.88.211/" + root);    
	    env.put(Context.SECURITY_AUTHENTICATION, "simple");
	    env.put(Context.SECURITY_PRINCIPAL, "cn=ldap,dc=dsideal,dc=com");
	    env.put(Context.SECURITY_CREDENTIALS, "123456");
	    DirContext ctx = null;
	    try {
	      ctx = new InitialDirContext(env);
	      System.out.println("认证成功");
	    }
	    catch (javax.naming.AuthenticationException e) {
	      e.printStackTrace();
	      System.out.println("认证失败");
	    }
	    catch (Exception e) {
	      System.out.println("认证出错：");
	      e.printStackTrace();
	    }
	 
	    if (ctx != null) {
	      try {
	        ctx.close();
	      }
	      catch (NamingException e) {
	        //ignore
	      }
	    }
	    System.exit(0);
	  }

}
