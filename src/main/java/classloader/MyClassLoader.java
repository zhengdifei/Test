package classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyClassLoader extends ClassLoader{
	
	 
 	 protected Class<?> findClass(String name) throws ClassNotFoundException {   
//	      String destPath = "D:\\project_room\\workspace3.5\\Test\\build\\classes\\classloader\\lib";   
//	      String className = name.substring(name.lastIndexOf('.') + 1);   
//	      String classFileName = destPath + "\\" + className + ".class";   
//          InputStream fileInStream = null;   
//	      ByteArrayOutputStream byteArrayOutStream = null;   
 		String classPath = "classloader/lib/"+name.replace(".", "/")+".class";
        InputStream fileInStream = null;   
	    ByteArrayOutputStream byteArrayOutStream = null;   
	         try {
	        	fileInStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(classPath);
	            //fileInStream = new FileInputStream(classFileName);   
	            byteArrayOutStream = new ByteArrayOutputStream();   
	            encrypt(fileInStream, byteArrayOutStream);   
	            byte[] classByte = byteArrayOutStream.toByteArray();   
	            return defineClass(classByte, 0, classByte.length);   
	        } catch (Exception e) {   
	             e.printStackTrace();   
	         } finally {   
	             try {   
	                byteArrayOutStream.close();   
	                fileInStream.close();   
	              } catch (IOException e) {   
	                e.printStackTrace();   
	            }   
	         }   
	         return super.findClass(name);   
	 }
 	 
 	 
	 /**  
	       * 用来加密class文件  
	     *   
	       * @param args  
	      */  
	      public static void main(String[] args) {   
	    	  // 原class文件   
	          String srcPath = "D:\\project_room\\workspace3.5\\Test\\build\\classes\\classloader\\MyClassTest.class";   
	         // 加密class文件存放目录   
	         String destPath = "D:\\project_room\\workspace3.5\\Test\\build\\classes\\classloader\\lib";   
	         InputStream fileInStream = null;   
	         OutputStream fileOutStream = null;   
	        try {   
	             fileInStream = new FileInputStream(srcPath);   
	             String fileName = srcPath.substring(srcPath.lastIndexOf('\\') + 1);   
	              String destFilePath = destPath + "\\" + fileName;   
	             fileOutStream = new FileOutputStream(destFilePath);   
	             encrypt(fileInStream, fileOutStream);   
	          } catch (Exception e) {   
	              e.printStackTrace();   
	          } finally {   
	              try {   
	                fileOutStream.close();   
	                fileInStream.close();   
	              } catch (IOException e) {   
	                  e.printStackTrace();   
	             }   
	         }   
	     }   
	   
	    /**  
	      * 对class文件加（解）密  
	      *   
	       * @param inStream  
	       *            class文件输入流  
	       * @param outStream  
	       *            class文件输出流  
	       */  
	     public static void encrypt(InputStream inStream, OutputStream outStream)   
	            throws Exception {   
	          int b = -1;   
	         while ((b = inStream.read()) != -1) {   
	              outStream.write(b ^ 0xff);   
	         }   
	     }   
}
