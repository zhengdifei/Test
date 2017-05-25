package TCP.setTcpNoDelay;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class EchoServer {
	private int port = 8000;
	private ServerSocket serverSocket;

	public EchoServer() throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("服务器启动");
	}

	public String echo(String msg) {
		return "echo:" + msg;
	}

	private PrintWriter getWriter(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	}

	private BufferedReader getReader(Socket socket) throws IOException {
		InputStream socketIn = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn));
	}

	DataInputStream in = null;
	DataOutputStream out = null;
	byte[] bytes = new byte[1024*2];

	public void service() {
		while (true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept(); // 等待客户连接
				System.out.println("New connection accepted "
						+ socket.getInetAddress() + ":" + socket.getPort());
//				BufferedReader br = getReader(socket);
//				PrintWriter pw = getWriter(socket);
				socket.setTcpNoDelay(true);
				socket.setTcpNoDelay(true);
				socket.setTrafficClass(0x1E);
				socket.setPerformancePreferences(1,2,0);
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());

				String msg = null;
				byte[] outputBuffer = new byte[70];
				new Random().nextBytes(outputBuffer);
				
				int off = 5;

				// while ((msg = br.readLine()) != null) {
				while(true){
					int len = 0; 
					if(read(bytes,4) == 4){
						len = (bytes[0]&0xff)<<24 | (bytes[1]&0xff)<<16 | (bytes[2]&0xff)<< 8 | (bytes[3] & 0xff) ;
					}
//					System.out.println("length:"+len);
					while(off<len){
						read(bytes, off) ;
						len = len - off;
					}
					if(read(bytes, len) == len) {
						// System.out.println(msg);
						out.write(outputBuffer, 0, 70);
						// if (msg.equals("bye")) //如果客户发送的消息为“bye”，就结束通信
						// break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null)
						socket.close(); // 断开连接
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int read(byte[] bytes, int len) throws IOException {
		int l = 0;
		int l2 = 0;
		do {
			l2 = in.read(bytes, l, len - l);
			if (l2 < 0)
				return -1;
			l = l + l2;
		} while (l < len);
		return l;
	}

	public static void main(String args[]) throws IOException {
		new EchoServer().service();
	}
}
