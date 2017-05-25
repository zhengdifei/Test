package TCP.setTcpNoDelay;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Sersocket {
	public static void main(String[] args) throws IOException, InterruptedException {
			ServerSocket ser = new ServerSocket(9999);
			while(true){
				Socket soc =ser.accept();
				soc.setSoTimeout(300);
//				Thread.sleep(30000);
				InputStream input = soc.getInputStream();
				OutputStream out =soc.getOutputStream();
				byte[] buf = new byte[10];
				int len = 0;
				int r = 0;
				try {
//					while( (len = input.read(buf, 0, buf.length))!=-1){
//						 System.out.println(new String (buf));
//						 buf = new byte[10];
//					 }
//					Thread.sleep(3000);
					System.out.println("start read");
					while((input.read(buf))!=-1){
						r++;
						System.out.println(input.available());
						out.write(r);
					}
					System.out.println("start end");
				} catch (IOException e) {
					e.printStackTrace();
				}
//				System.out.println(r);
				System.out.println("\n处理完毕");
				input.close();
				soc.close();
			}
		
		
	}
}