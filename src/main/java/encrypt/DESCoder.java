/**  
 * 概要:DES加密算法，兼容PHP的解密  
 * @author zdf  
 * @since v2.0  
 */  
package encrypt;   
  
import java.io.IOException;   
import java.security.SecureRandom;   
  
import javax.crypto.Cipher;   
import javax.crypto.SecretKey;   
import javax.crypto.SecretKeyFactory;   
import javax.crypto.spec.DESKeySpec;   
  
import sun.misc.BASE64Decoder;   
import sun.misc.BASE64Encoder;   
  
public class DESCoder    
{   
    private final static String KEY = "SSOFOUNDER"; // 字节数必须是8的倍数   
    public static byte[] desEncrypt(byte[] plainText) throws Exception   
    {   
        SecureRandom sr = new SecureRandom();   
        DESKeySpec dks = new DESKeySpec(KEY.getBytes());   
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");   
        SecretKey key = keyFactory.generateSecret(dks);   
        Cipher cipher = Cipher.getInstance("DES");   
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);   
        byte data[] = plainText;   
        byte encryptedData[] = cipher.doFinal(data);   
        return encryptedData;   
    }   
       
    public static byte[] desDecrypt(byte[] encryptText) throws Exception    
    {   
        SecureRandom sr = new SecureRandom();   
        DESKeySpec dks =  new DESKeySpec(KEY.getBytes());   
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");   
        SecretKey key = keyFactory.generateSecret(dks);   
        Cipher cipher = Cipher.getInstance("DES");   
        cipher.init(Cipher.DECRYPT_MODE, key, sr);   
        byte encryptedData[] = encryptText;   
        byte decryptedData[] = cipher.doFinal(encryptedData);   
        return decryptedData;   
    }   
       
    public static String encrypt(String input) throws Exception   
    {   
        return base64Encode(desEncrypt(input.getBytes()));   
    }   
       
    public static String decrypt(String input) throws Exception    
    {   
        byte[] result = base64Decode(input);   
        return new String(desDecrypt(result));   
    }   
       
    public static String base64Encode(byte[] s)    
    {   
        if (s == null)   
            return null;   
        BASE64Encoder b = new sun.misc.BASE64Encoder();   
        return b.encode(s);   
    }   
       
    public static byte[] base64Decode(String s) throws IOException    
    {   
        if (s == null)   
        {   
           return null;   
        }   
        BASE64Decoder decoder = new BASE64Decoder();   
        byte[] b = decoder.decodeBuffer(s);   
        return b;   
    }   
    public static  void main(String args[]) {   
        try {   
            //System.out.println(DESCoder.encrypt("汉字"));   
            //System.out.println(DESCoder.decrypt(DESCoder.encrypt("汉字ssssssssssssssssssssssssss")));   
        	System.out.println(DESCoder.encrypt("123456 123111"));
        	System.out.println(DESCoder.encrypt("123456 123"));
        	
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
    }   
}