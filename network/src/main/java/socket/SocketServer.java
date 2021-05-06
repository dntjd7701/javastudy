package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static final int PORT = 2415;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(PORT);
			
			//서버를 실행한 컴퓨터의 ip주소와 port를 가지고 서버를 구성하게됌!!
			//ip 는 로컬이니깐, 포트 설정
			System.out.println("socket : " + PORT + "으로 서버가 열렸땅!!!! ");
			
			while(true) {
				Socket socket = serverSocket.accept();
				
				// 소켓 서버로 접속시 접속자 정보 소켓에 저장
				
				// 접속자의 local address 가져오기 
				System.out.println("Client 입장 : " + socket.getLocalAddress() + ":" + socket.getPort());
				
			
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
