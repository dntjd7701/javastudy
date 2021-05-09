package mychat.gui;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyGUIChatServer {

	public static void main(String[] args) {

		// ServerSocket 생성 및 초기
		ServerSocket serverSocket = null;
		
		// client들을 담을 ArrayList 객체 생성
		// Client들을 담을 거 
		ArrayList<PrintWriter> listWriters = new ArrayList<>();
		
		
		
		// serverSocket객체 생성 
		try {
			serverSocket = new ServerSocket();
			
			// 자신이 받을 아이피, 포트 설정
			// "0.0.0.0"으로 모든 ip에 대해서 개방, 포트 번호는 임의 설정
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 1231));
			
			
			while(true) {
				// 클라이언트들의 연결을 계속해서 기다리는 부분
				Socket socket = serverSocket.accept();
				
				// Thread 시작하기 
				new MyGUIChatServerThread(socket, listWriters).start();
			}
			
			
		} catch (IOException e) {
			System.out.println("[연결 실패] : " + e);
		} finally {
			if(serverSocket != null && !serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		
	}

}
