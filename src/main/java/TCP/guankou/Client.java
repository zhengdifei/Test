package TCP.guankou;

import java.io.*;

import java.net.*;

public class Client {

	public static void main(String args[]) {

		try {
			// 向本机的4700端口发出客户请求
			Socket socket = new Socket("127.0.0.1", 4700);

			//协议说明
			StringBuffer sb = new StringBuffer();
			sb.append("[1]获取当前分站记录总数\n");
			sb.append("[input]TOTAL_R:20160101121212\n");
			sb.append("[output]TOTAL_R:101\n");
			sb.append("\n");
			sb.append("[2]开启或关闭分站自动校验功能(1:开启,0:关闭)\n");
			sb.append("[input]AutoCheck:1\n");
			sb.append("[output]AutoCheck:1\n");
			sb.append("\n");
			sb.append("[3]主站控制分站远程自动校验某一回路[X:第几块电表]\n");
			sb.append("[input]METER_TEST:1\n");
			sb.append("[output]METER_TEST:1\n");
			sb.append("\n");
			sb.append("[4]主站控制分站远程自动抄读某一回路电能表计量数据[X:第几块电表]\n");
			sb.append("[input]METER_READ:1\n");
			sb.append("[output]METER_READ:1\n");
			sb.append("\n");
			sb.append("[5]获取分站检定数据\n");
			sb.append("[input]BASE_R:20161011121212\n");
			sb.append("[output]LAST_D:01\n");
			sb.append("[output]BASE_R:xxxxxx,xxxxxx\n");
			sb.append("\n");
			sb.append("[6]获取分站下一条检定数据命令5的延续\n");
			sb.append("[input]REP_D:00\n");
			sb.append("[output]LAST_D:01\n");
			sb.append("[output]BASE_R:xxxxxx,xxxxxx\n");
			sb.append("\n");
			sb.append("[7]获取分站电能表电量等数据\n");
			sb.append("[input]DOWN_METER:20160101121212\n");
			sb.append("[output]LAST_D:01\n");
			sb.append("[output]BASE_R:xxxxxx,xxxxxx\n");
			sb.append("\n");
			sb.append("[8]获取分站下一条电能表电量等数据命令7的延续\n");
			sb.append("[input]DOWN_METER:00\n");
			sb.append("[output]LAST_D:01\n");
			sb.append("[output]BASE_R:xxxxxx,xxxxxx\n");
			sb.append("\n");
			
			System.out.println(sb.toString());
			
			// 由系统标准输入设备构造BufferedReader对象
			BufferedReader sin = new BufferedReader(new InputStreamReader(
					System.in));
			
			// 由Socket对象得到输出流，并构造PrintWriter对象
			PrintWriter os = new PrintWriter(socket.getOutputStream());

			// 由Socket对象得到输入流，并构造相应的BufferedReader对象
			BufferedReader is = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			
			// 从系统标准输入读入一字符串
			String readline = sin.readLine(); 
			
			// 若从标准输入读入的字符串为 "bye"则停止循环
			while (!readline.equals("bye")) {

				// 刷新输出流，使Server马上收到该字符串
				System.out.println("[input]" + readline);
				
				// 将从系统标准输入读入的字符串输出到Server
				os.println(readline);
				os.flush();

				// 从Server读入一字符串，并打印到标准输出上
				System.out.println("[output]" + is.readLine());

				// 从系统标准输入读入一字符串
				readline = sin.readLine(); 
			} // 继续循环

			os.close(); // 关闭Socket输出流

			is.close(); // 关闭Socket输入流

			socket.close(); // 关闭Socket

		} catch (Exception e) {

			System.out.println("Error" + e); // 出错，则打印出错信息

		}

	}

}
