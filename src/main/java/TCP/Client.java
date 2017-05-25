package TCP;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;

public class Client {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	//定义一个private的socket，服务器ip:127.0.0.1,端口号：19860。
	private Socket clientsocket = new Socket("127.0.0.1",19860);
	//必须抛出异常。
	public Client() throws UnknownHostException, IOException {
		
	}
	//取得socket。
	public Socket getClientsocket() {
		return clientsocket;
	}

	//设置socket
	public void setClientsocket(Socket clientsocket) {
		this.clientsocket = clientsocket;
	}

	public static void main(String[] args) throws UnknownHostException, IOException{
			Client client = new Client(); //实例化一个client。
			
			//建立传输流
			//传输数据到服务器。
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getClientsocket().getOutputStream())));
			//接受服务器传递过来的数据。
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getClientsocket().getInputStream()));
			new ReadMessage(in);
			//自己在控制台写数据，传递给哦0ut，然后，可以传递给服务器。
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			//System.out.print(in.readLine());
			out.println(input.readLine());
			out.flush();
			//System.out.println(in.readLine());
			while(true){
				 String strinput = input.readLine();//读入控制台的一条数据。
				 out.println(strinput);//向服务器传输。
				 out.flush();//清空缓存。
				 //String strin = in.readLine();//读取传递回来的一行数据。
				 //System.out.println(strin); //打印传回的数据。
			}
	}
}

class ReadMessage extends Thread{
	BufferedReader in = null;
	public ReadMessage(BufferedReader in){
		this.in = in;
		start();
	}
	
	public void run(){
		while(true){
			try {
				String strin = in.readLine();
				
				if(strin == null)break;
				
				System.out.println(strin); //打印传回的数据。
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}