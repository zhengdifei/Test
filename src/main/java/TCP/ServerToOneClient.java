package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class ServerToOneClient  extends Thread {
	
	private ServerToMultiClient server = null;
	private Socket socket = null;
	private String clientName = null;
	private PrintWriter out;
	private BufferedReader in;
	
	public ServerToOneClient(Socket socket,ServerToMultiClient server) throws UnknownHostException,IOException{
		this.server = server;
		this.socket = socket;
		start();
	}
	//取socket
	public void run(){
		try {			
			//建立传输流
			//输出流，向建立socket的客户端，传输数据。
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			//输入流，接受socket服务器端的数据。
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println("您好！请输入你的登陆名：");
			out.flush();
			//监听
			clientName = in.readLine();
			server.users.add(clientName);
			server.userClient.put(clientName, this);
			
			System.out.println(clientName + " 登录了！"); 
			out.println("hello,"+clientName+" 欢迎登陆flying空间！");
			out.flush();
			while(true){
				String str = in.readLine(); //读入一条数据。
				if(str.equals("quit")){
					break;  //“quit”退出。
				}else if(str.equals("users")){
					StringBuffer sb = new StringBuffer();
					for(int i = 0;i<server.users.size();i++){
						sb.append(server.users.get(i) + " ");
					}
					
					out.println(sb.toString());
					out.flush();
				}else if(str.contains("@")){
					String[] val = str.split("@");
					ServerToOneClient oneClient = server.userClient.get(val[1]);
					if(oneClient == null){
						out.println("没有这个人，消息发送失败！");
						out.flush();
					}else{
						oneClient.out.println(clientName + ":" + val[0]);
						oneClient.out.flush();
					}
				}else{
					System.out.println(str);  //控制台输出接受的数据
					out.println("[漂流瓶]"+str);
					out.flush();
				}
			}
			socket.close(); //退出后，关闭socket。
		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} //实例化一个server
		
	}
	
	public BufferedReader getIn() {
		return in;
	}
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	public PrintWriter getOut() {
		return out;
	}
	public void setOut(PrintWriter out) {
		this.out = out;
	}
}
