package myecho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class MyEchoClient {

	private static final String IP = "127.0.0.1";
	private static final int PORT = MyEchoServer.PORT;

	public static void main(String[] args) {
		Socket socket = null;
		Scanner scanner = null;

		try {
			scanner = new Scanner(System.in);
			socket = new Socket();

			socket.connect(new InetSocketAddress(IP, PORT));
			log("연결 완료 ");

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
			
			while(true) {
				System.out.println(">>");
				String line = scanner.nextLine();
				
				
				if("exit".equals(line)) {
					break;
				}
				
				
				pw.println(line);
				
				String data = br.readLine();
				if(data == null) {
					log("closed by client");
					break;
				}
				
				System.out.println("<<<" +  data);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(scanner != null) {
					scanner.close();
				}
				if(socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private static void log(String log) {
	 System.out.println("[MyEchoClient] " + log);
	}
}
