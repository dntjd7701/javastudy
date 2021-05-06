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

	public static final int PORT = 9000;

	public static void main(String[] args) {

		// 1. 서버 소켓 생성 
		ServerSocket serverSocket = null;
		List<Writer> listWriters = new ArrayList<Writer>();

		try {
			serverSocket = new ServerSocket();

			// (지역)호스트의 ip주소 반환, 호스트의 ip주소 반환
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			log("연결중........" + hostAddress + ":" + PORT);
			
			
			// 3. 요청 대기 
			while(true) {
				Socket socket = serverSocket.accept();
				Thread thread = new ChatServerThread(socket, listWriters);
				thread.start();
				
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
