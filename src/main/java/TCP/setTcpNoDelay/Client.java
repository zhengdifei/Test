package TCP.setTcpNoDelay;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Client {
	public static void main(String[] args) throws InterruptedException {
		try {
			Socket soc = new Socket("127.0.0.1",9999);
			OutputStream out =soc.getOutputStream();
			InputStream in =soc.getInputStream();
			int i=0;
			while(i++<2){
				out.write("hello wordhello wordhello wordhello wordhello wordhello wordhello wordhello wordhello wordhello wordhello word".getBytes());
			}
			byte[] buf = new byte[1024];
			int max =26;
			while( i <  max){
				i += in.read(buf,i,buf.length-i);
			}
			System.out.println("写完");
			Thread.sleep(9000);
			
//			System.out.print("d");
//			System.out.print("d");
//			System.out.print("连接成功");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
}