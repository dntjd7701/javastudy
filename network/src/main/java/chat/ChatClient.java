package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) {
		Scanner sc = null;
		Socket socket = null;
		try {
			sc = new Scanner(System.in);
			socket = new Socket();
			socket.connect(new InetSocketAddress("127.0.0.1", 6910));
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
			
			System.out.print("닉네임 >> ");
			String nickname = sc.nextLine();
			pw.println("join:"+ nickname);
			
			// thread
			new ChatClientThread(nickname,br).start();
			
			while(true) {
				System.out.print(">> ");
				String input = sc.nextLine();
				
				if("quit".equals(input)) {
					pw.println("quit:");
				}else {
					pw.println("message:"+input);
				}
			}
			
		}catch (IOException e) {
			log("error" + e);
		} finally {
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();
				}
				
				if(sc != null) {
					sc.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static final void log(String log) {
		System.out.println("[ChatClient] "+ log);
	}
}