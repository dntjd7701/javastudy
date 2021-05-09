package chat.gui;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GUIChatServer {

	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		List<PrintWriter> listWriters = new ArrayList<>();
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 6910));
			
			while(true) {
				Socket socket = serverSocket.accept();
				new GUIChatServerThread(socket,listWriters).start();;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(serverSocket != null && !serverSocket.isClosed())
					serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
