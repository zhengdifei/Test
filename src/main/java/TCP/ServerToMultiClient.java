package TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class ServerToMultiClient extends Thread{
	public ServerSocket server = new ServerSocket(19860);//建立一个serversocket，端口号是19860。
	public List<String> users = new ArrayList<String>();
	public List<ServerToOneClient> clients = new ArrayList<ServerToOneClient>();
	public Map<String,ServerToOneClient> userClient = new HashMap<String,ServerToOneClient>();
	
	
	public ServerToMultiClient() throws IOException{
			start();
	}
	
	//取socket
	public void run(){
		
		try {
			
			
//			System.out.println("socket.getLocalPort():" + socket.getLocalPort());
//			System.out.println("socket.getLocalAddress():" + socket.getLocalAddress());
//			System.out.println("socket.getLocalSocketAddress():" + socket.getLocalSocketAddress());
//			System.out.println("socket.getPort():" + socket.getPort());
//			System.out.println("socket.getInetAddress():" + socket.getInetAddress());
//			System.out.println("socket.toString():" + socket.toString());
			
			while(true){//循环监听
				Socket socket = server.accept();
				ServerToOneClient onClient = new ServerToOneClient(socket,this);
				clients.add(onClient);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 	try {
				new ServerToMultiClient();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
