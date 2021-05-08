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

	private static final int PORT = 6910;
	public static void main(String[] args) {
		
		List<Writer> listWriters = new ArrayList<>();
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("연결 기다림 " + hostAddress + ":" + PORT);
			
			while(true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket,listWriters).start();
			}
		}catch (IOException e) {
			log("Error" + e);
		}finally {
			try {
				if(serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static final void log(String log) {
		System.out.println("[ChatServer] " + log);
	}
}
