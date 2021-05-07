package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

	public static final int PORT = 9001;

	public static void main(String[] args) {

		// 1. 서버 소켓 생성 
		ServerSocket serverSocket = null;
		Socket socket = null;
		List<Writer> listWriters = new ArrayList<Writer>();

		try {
			serverSocket = new ServerSocket();
			
			
			// 2. bind
			// (지역)호스트의 ip주소 반환, 호스트의 ip주소 반환
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			// 서버 오픈 확인용 
			log("야생의 socket / " + "0.0.0.0" + ":" + PORT + " / 이 열렸다 !");
			
			// 3. 요청 대기 
			while(true) {
				// 서버에 클라이언트 접속
				socket = serverSocket.accept();
				
				new ChatServerThread(socket, listWriters).start();
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void log(String log) {
		System.out.println("[서버##] " + log);
	}

}
