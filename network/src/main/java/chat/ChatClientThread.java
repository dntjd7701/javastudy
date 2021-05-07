package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientThread extends Thread {
	private BufferedReader br;
	
	Scanner sc = new Scanner(System.in); // 채팅용 , 서버로 메세지 보내는 thread
	Socket socket = null;

	
	public ChatClientThread(Socket socket, BufferedReader br) {
		this.br = br;
		this.socket = socket;

	}

	@Override
	public void run() {
		try {
			
			
			// 메시지 입력 
			
			PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = br.readLine();
			
			// join 성공 확인 !!
			String echo = br.readLine();
			String[] tokens = echo.split(":");
			if ("join".equals(tokens[0])) {
				System.out.println(tokens[1]);
			}
			
			
			while(true){
				pw.println(sc.nextLine());
				
				if(line != null) {
				System.out.println(br.readLine());
				}
			}

			
			
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null && socket.isClosed() == false) {
					socket.close();
				} 
				if(br != null) {
					br.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
