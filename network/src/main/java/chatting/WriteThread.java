package chatting;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread { // 서버로 메세지 보내는 thread

	Socket socket = null;
	Scanner sc = new Scanner(System.in); // 채팅용 
	
	
	public WriteThread(Socket socket) {
		this.socket = socket;
	}


	@Override
	public void run() {
		try {
		PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
		
		while(true) {
			pw.println(sc.nextLine());
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}


