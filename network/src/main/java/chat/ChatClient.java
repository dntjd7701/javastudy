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
	private static final String IP = "127.0.0.1";
	private static final int PORT = ChatServer.PORT;

	public static void main(String[] args) {

		Scanner scanner = null;
		Socket socket = null;

		try {

			// 1. 키보드 연결
			scanner = new Scanner(System.in);

			// 2. 소켓 생성
			socket = new Socket();
			

			// 3. 서버 연결

			while(true) {
			socket.connect(new InetSocketAddress(IP, PORT));

			
			
			// 4. reader/writer 생성
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			
			// 5. join protocol
			// 닉네임 받아서 서버 쪽으로 보냄. 풀에 저장될거
			
			String nickname = scanner.nextLine();
			pw.println("join:" + nickname);
		
			
			
			// 6. ChatClientReceiveThread 시작
			Thread clientThread = new ChatClientThread(socket, br);
			clientThread.start();
			
			
			
			// 7. 키보드 입력 처리
			

				String input = scanner.nextLine();
				pw.println(input);
				
				if ("quit".equals(input) == true) {
					pw.println("quit:" + nickname);
					break;
				} else {
					// 9. message
					pw.println("message:" + input);
				}
			}
			
			
			
			
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (scanner != null) {
					scanner.close();
				}
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
