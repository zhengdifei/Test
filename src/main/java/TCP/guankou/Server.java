package TCP.guankou;

import java.io.*;

import java.net.*;

public class Server {

	public static void main(String args[]) {

		try {

			ServerSocket server = null;

			try {
				// 创建一个ServerSocket在端口4700监听客户请求
				server = new ServerSocket(4700);
			} catch (Exception e) {
				// 出错，打印出错信息
				System.out.println("can not listen to:" + e);
			}

			Socket socket = null;

			try {
				// 使用accept()阻塞等待客户请求，有客户
				// 请求到来则产生一个Socket对象，并继续执行
				socket = server.accept();

			} catch (Exception e) {
				// 出错，打印出错信息
				System.out.println("Error." + e);
			}

			
			// 由Socket对象得到输入流，并构造相应的BufferedReader对象
			BufferedReader is = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			// 在标准输出上打印从客户端读入的字符串
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			
			// 如果该字符串为 "bye"，则停止循环
			String line = is.readLine();
			
			while (!line.equals("bye")) {

				// 刷新输出流，使Client马上收到该字符串
				System.out.println("[input]" + line);
				
				String outputStr = "";
				if(line.startsWith("TOTAL_R:")){
					outputStr = "TOTAL_R:" + Math.round(100 * Math.random());
				}else if(line.startsWith("AutoCheck")){
					outputStr = line;
				}else if(line.startsWith("METER_TEST")){
					outputStr = line;
				}else if(line.startsWith("METER_READ")){
					outputStr = line;
				}else if(line.startsWith("BASE_R")){
					outputStr = "LAST_D:01";
				}else if(line.startsWith("REP_D")){
					outputStr = "LAST_D:01";
				}else if(line.startsWith("DOWN_METER")){
					outputStr = "LAST_D:01";
				}else if(line.startsWith("REP_METER")){
					outputStr = "LAST_D:01";
				}else{
					outputStr = "未定义的命令：" + line;
				}
				
				System.out.println("[output]" + outputStr);
				// 向客户端输出该字符串
				os.println(outputStr);
				os.flush();
				
				// 从Client读入一字符串，并打印到标准输出上
				line = is.readLine();
				
			} // 继续循环

			os.close(); // 关闭Socket输出流

			is.close(); // 关闭Socket输入流

			socket.close(); // 关闭Socket

			server.close(); // 关闭ServerSocket

		} catch (Exception e) {

			System.out.println("Error:" + e);

			// 出错，打印出错信息

		}

	}

}
