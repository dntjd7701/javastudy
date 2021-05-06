package chatting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MySocketServer {

	private static final int PORT = 1234;

	public static void main(String[] args) {

		try {
			// 서버 소켓 만들
			ServerSocket serverSocket = new ServerSocket(PORT);

			// 서버 오픈 확인용
			System.out.println("socket : " + serverSocket + "으로 서버가 열렸다 !!!!");

			// 소켓 서버가 종료될 때까지 무한 루프
			while (true) {
				// 서버에 클라이언트 접속
				Socket socket = serverSocket.accept();

				// Thread 안에 클라이언트 정보를 담았음.

				// socket 정보 thread에 전
				Thread thread = new MySocketServerThread(socket);
				thread.start(); // Thread 시작

			}

		} catch (IOException e) {
			e.printStackTrace(); // 예외 처
		}
	}

}
