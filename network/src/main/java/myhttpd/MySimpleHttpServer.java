package myhttpd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import httpd.RequestHandler;

public class MySimpleHttpServer {
	private static final int PORT = 7777;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("0.0.0.0",PORT));
			log("시작합니데이");
			
			while(true) {
				Socket socket = serverSocket.accept();
				
				new RequestHandler(socket).start();
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(serverSocket != null && serverSocket.isClosed() == false) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					System.out.println("ERRRRRRORRRR");
				}
			}
		}
		
	}

	public static void log(String message) {
		System.out.println("[SERVER yeaahhhh]:[" + Thread.currentThread().getId() + "]" + message);
	}
}
