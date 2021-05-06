package mynetworkpractice;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyEchoServer {

	public static final int PORT = 7000;

	public static void main(String[] args) {
		// Socket 클래스는 client에서 서버로 접속하거나 Server에서 accept 하는데 필요한 클래스

		// 1. 클라이언트의 요청을 받기 위한 준비를 한다. ServerSocket
		ServerSocket serverSocket = null;

		// 서버 소켓 생성
		try {
			serverSocket = new ServerSocket();

			// 2. 바인딩 작업 (Binding)
			// 주소와 포트를 잡아두는거임
			// 0.0.0.0 으로 설정함으로써 모두 받을 수 있도록 작업함.

			// InnetSocketAddress
			// 이 클래스는 IP 소켓 주소 (IP 주소 + 포트 번호)를 구현
			// 이 클래스는 페어 (호스트명 + 포트 번호)로 할 수도 있어
			// 그 경우 호스트명의 해결이 시도.
			// 해결에 실패하면 주소는 「미해결」이 된다.
			// 프록시 경유의 접속과 같은 일부의 환경에서는 아직 사용할 수 있다.
			// 이 클래스는 바인드 또는 접속을 위해서 소켓이 사용한다.
			// 또는 반환값으로서 사용하는 변경 불가능한 객체를 제공합니다.
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			
			Log("starts ..... [port : " + PORT + "]");
			
			
			while(true) {
				// 3. accept
				// 클라이언트의 요청을 받아들
				// 이 때 클라이언트는 서버에 접속 요청을 함(Socket)
				Socket socket = serverSocket.accept();
				Thread thread = new MyEchoServerReceiveThread(socket);
				thread.start();
			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void Log(String log) {
		System.out.println("[EchoServer] " + log);
		
	}
}
