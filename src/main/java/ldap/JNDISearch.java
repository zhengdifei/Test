package ldap;

import java.util.*;

//引入LDAP的包  
import java.util.Enumeration;  
import java.util.Hashtable;  
  
import javax.naming.Context;  
import javax.naming.NamingEnumeration;  
import javax.naming.directory.Attribute;  
import javax.naming.directory.Attributes;  
import javax.naming.directory.DirContext;  
import javax.naming.directory.InitialDirContext;  
import javax.naming.directory.SearchControls;  
import javax.naming.directory.SearchResult; 

import org.apache.commons.lang.StringUtils;

public class JNDISearch {

	public static String INITCTX = "com.sun.jndi.ldap.LdapCtxFactory"; // 驱动  
	  
    public static String MY_HOST = "ldap://192.168.88.211:389"; // 主机地址和端口  
  
    public static String MY_SEARCHBASE = "dc=guessant,dc=org"; // 基点入口  
  
    public static String MY_FILTER = "uid=txhzm"; // 过滤条件  
  
    public static String MGR_DN = "cn=Manager,dc=guessant,dc=org"; // 用户名  
  
    public static String MGR_PW = "secret"; // 密码  
  
    public static String MY_ATTRS[] = { "uid","cn","userpassword","mail"};  
  
    // StringBuffer res = new StringBuffer(); //用来输入名字，IP地址的对象  
    public static String temp = new String();  
  
    public String search() throws Exception {  
        StringBuffer res = new StringBuffer();  
        try {  
            // 建立连接  
            Hashtable env = new Hashtable();  
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITCTX);  
            env.put(Context.PROVIDER_URL, MY_HOST);  
            env.put(Context.SECURITY_AUTHENTICATION, "simple"); // 使用简单认证来认证用户  
            env.put(Context.SECURITY_PRINCIPAL, MGR_DN);  
            env.put(Context.SECURITY_CREDENTIALS, MGR_PW);  
            DirContext ctx = new InitialDirContext(env);  
  
            // 设置查询范围并开始查询  
            SearchControls constraints = new SearchControls();  
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);  
            NamingEnumeration results = ctx.search(MY_SEARCHBASE, MY_FILTER,  
                    constraints);  
  
            // 打印查询结果  
            while (results != null && results.hasMore()) {  
                SearchResult sr = (SearchResult) results.next();  
                String dn = sr.getName();  
                if(StringUtils.isBlank(dn)) continue;  
                dn = dn + "," + MY_SEARCHBASE;  
                System.out  
                        .println("==============================================");  
                System.out.println("Distinguished Name is: " + dn);  
  
                // 打印指定的字段//////////////////////////////////////////////////////////////////  
                Attributes ar = ctx.getAttributes(dn, MY_ATTRS);  
                if (ar == null) {  
                    // 对应的uid没有多余的属性  
                    System.out.println("Entry " + dn  
                            + " has none of the specified attributes\n");  
                } else {  
                    // 开始显示对应的字段  
                    for (int i = 0; i < MY_ATTRS.length; i++) {  
                        Attribute attr = ar.get(MY_ATTRS[i]);  
                        if (attr != null) {  
                            System.out.print(MY_ATTRS[i] + " : ");  
                            for (Enumeration vals = attr.getAll(); vals  
                                    .hasMoreElements();) {  
                                Object obj = vals.nextElement();  
                                  
                                System.out.println("\t" + obj);  
                                res.append(temp + "/");  
                            }  
                        }  
                        System.out.println("\n");  
                    }  
                    // /////////////////////////////////////////////////////////////////////////////////  
  
                    /* 
                     * 打印全部的字段/////////////////////////////////////////////////////////////////// 
                     * Attributes attrs = sr.getAttributes(); 
                     * for(NamingEnumeration ne = attrs.getAll(); 
                     * ne.hasMoreElements(); ){ Attribute attr = (Attribute) 
                     * ne.next(); String attrID = attr.getID(); 
                     * System.out.println(attrID+": "); for(Enumeration vals = 
                     * attr.getAll();vals.hasMoreElements(); ){ 
                     * System.out.println("\t"+vals.nextElement()); } 
                     */// ///////////////////////////////////////////////////////////////////////////////  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.exit(1);  
        }  
        System.out.println(res.toString() + "\n\n\n\n ---- end ----");  
  
        // splitString sp = new splitString();  
        // System.out.println("一共有"+sp.splitString(res.toString()).length+"个返回");  
        // //打印显示结果，计算返回的数组值  
        //return sp.splitString(res.toString());  
        return res.toString();  
  
    }  
  
    ///////////////////////////////////////////////////////////////////////////////////////////  
    // 使用正则表达式来分拣提取的字符串 ///////////////////////////////////  
    ///////////////////////////////////////////////////////////////////////////////////////////  
  
      
      
    public static void main(String args[]) {  
        JNDISearch search = new JNDISearch();  
        //System.out.println(myMail.sendMail("libem@163.com", "this is test",   "my \n test"));  
        try {  
            System.out.println(search.search());  
        } catch (Exception e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }   
}
