package encrypt;

import java.security.MessageDigest;

public class MessageDigestTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	    //7c4a8d9ca3762af61e59520943dc26494f8941b:123456
		//1edd72aad695cf469832e2d473dca2eecd5ef9:12345611
		//b3bdd4d2a35f43f879bc23247af088a19cc78f7:1234561111
		String password="zdf";
	    byte[] plainText = password.getBytes("UTF-8");
	    MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
	    MessageDigest md5 = MessageDigest.getInstance("MD5");
	    sha1.update(plainText);
	    md5.update(plainText);
	    byte[] digest = sha1.digest();
	    byte[] digest2 = md5.digest();
	    System.out.println("--- Message Digest ---");
	    for(int i=0; i<digest.length; i++) {
	        System.out.printf("%x", digest[i]);// System.out.print(" ");
	    }
	    System.out.println();
	    for(int i=0; i<digest2.length; i++) {
	        System.out.printf("%x", digest2[i]);// System.out.print(" ");
	    }
	}

}
