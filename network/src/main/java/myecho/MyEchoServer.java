package myecho;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyEchoServer {
	public static final int PORT = 6000;

	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("시작...!! [port:" + PORT + "]");
			
			
			while(true) {
				Socket socket = serverSocket.accept();
				Thread thread = new MyEchoServerThread(socket);
				thread.start();
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void log(String log) {
		System.out.println("[MyEchoServer]" + log);
	}

}
