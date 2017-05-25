package TCP.setTcpNoDelay;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class EchoClient {
	private String host = "localhost";
	private int port = 8000;
	private Socket socket;

	public EchoClient() throws IOException {

	}

	public static void main(String args[]) throws IOException {
		new EchoClient().talk();
	}

	private PrintWriter getWriter(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	}

	private BufferedReader getReader(Socket socket) throws IOException {
		InputStream socketIn = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn));
	}
	
	PrintWriter pw = null;
	BufferedReader br = null;
	DataOutputStream out = null;
	DataInputStream in = null;
	public void talk() throws IOException {
		try {
			BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
			String msg = null;
			while (true) {
				System.out.print(">>");
				msg = localReader.readLine();
				// pw.println(msg);
				// System.out.println(br.readLine());
				if (msg.equals(""))
					continue;
				String[] p = msg.split(" ");
				if (p[0].equals("bye"))
					break;
				if (p[0].equals("send")) {
					if (socket == null || socket.isClosed() || !socket.isConnected()) {
						if(p.length<3){System.out.println("please enter ip and port!!"); continue;}
						socket = new Socket(p[1], Integer.parseInt(p[2]));
						socket.setSoTimeout(300000);
//						socket.setTcpNoDelay(true);
						socket.setTrafficClass(0x1E);
//						System.out.println(socket.getTrafficClass());
						socket.setPerformancePreferences(1,2,0);
//						br = getReader(socket);
//						pw = getWriter(socket);
						out = new DataOutputStream(socket.getOutputStream());
					    in = new DataInputStream(socket.getInputStream());
					}
					if(socket!=null && socket.isConnected() && !socket.isClosed()){
						
//						String m = "0123456789abcdefghijklmnopqrstuvwxyz123456789";
//							   m+="0123456789abcdefghijklmnopqrstuvwxyz123456789";
//							   m+="0123456789abcdefghijklmnopqrstuvwxyz123456789";
//							   m+="0123456789abcdefghijklmnopqrstuvwxyz123456789";
//							   m+="0123456789abcdefghijklmnopqrstuvwxyz123456789";
//							   m+="0123456789abcdefghijklmnopqrstuvwxyz123456789";
//							   m+="0123456789abcdefghijklmnopqrstuvwxyz123456789";
//							   m+="0123456789abcdefghijklmnopqrstuvwxyz123456789";
						byte[] bytes = new byte[45];
						new Random().nextBytes(bytes);
						int len  = bytes.length;
						byte[] outputBuffer = new byte[70];
						int i=0;
//						long t1 = 0;
						String s = "";
						int r = 0;
						int off = 20;
						byte[] l = new byte[]{(byte)((len>>24)&0xff),(byte)((len>>16)&0xff),(byte)((len>>8)&0xff),(byte)((len>>0)&0xff)};
						long t = System.currentTimeMillis();
						for(;i<1000;i++){
//							t1 = System.currentTimeMillis();
//							pw.println("0123456789abcdefghijklmnopqrstuvwxyz123456789");
							out.write(l,0,4);
							
							out.write(bytes,0,off);
							out.write(bytes,off,len-off);
//							out.flush();
//							s = br.readLine();
							
							r = read(outputBuffer, 70);
							if(r==-1) {
								socket.close();
								break;
							}
//							System.out.println(i);
//							if(s == null) {socket.close();};
							if(i%1000==0)System.out.println("senging:"+i+"  \tt:"+(System.currentTimeMillis() - t )+" ms \tr:"+r);
						}
						System.out.println("bytes is size:"+bytes.length+" c:"+ (i) +" time:" + (System.currentTimeMillis() - t)+"ms");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int read(byte[] bytes,int len) throws IOException{
		  int l =0;
		  int l2 = 0;
		  do{
			  l2 = in.read(bytes, l, len-l);
			  if(l2<0) return -1;
			  l = l +l2;
		  }while(l<len);
		  return l;
	  }
}
