package mychat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyChatServer {
	private static final int PORT = 5923;

	public static void main(String[] args) {
		
		List<Writer> listWriters = new ArrayList<Writer>();
		// Client의 Socket을 받아 자신의 Socket으로 정보를 넣기 위해 생성, 초기
		ServerSocket serverSocket = null;
		
		
		try {
			// 서버 소켓 객체 생성 
			serverSocket = new ServerSocket();
			// host의 주소 값을 받아 저장하는 변수 
			// InetAddress 클래스를 사용하여 ip를 받아오며, 
			// LocalHost Ip를 받고, String 의 형태로 변환한다.
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			
			//binding
			// "0.0.0.0" 으로 모든 IP로부터 접근이 가능하게 개방하고
			// 지정된 포트를 입력하여 붙잡는다.
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("I'm waiting for connection");
			log(hostAddress + ":" + PORT);
			
			// 연결이 끊기지 않도록 무한 루프를 돌
			while(true) {
				// serverSocket을 통해 받은! 소켓 정보!!!를 socket이라는 변수에 넣음 
				// 소켓 연결 완료 
				Socket socket = serverSocket.accept();
				log("클라이언트와 연결이 완료되었습니다.");
				
				// Thread 객체 생성 및 시작 
//				Thread thread = new MyChatServerThread(socket, listWriters);
//				thread.start();
				
				// 소켓 정보를 가지고 있는 소켓 변수와 리스트 변수를 파라미터로 넘겨줌 
				// 리스트 변수는 Writer 형을 가지고 있음 
				new MyChatServerThread(socket, listWriters).start();
				log("MyChatServerThread 시작");
	
			}
			
		} catch (IOException e) {
			log("Error !!!" + e);
		} finally {
			//자원 정리 
			// serverSocket 닫아주기
			// serverSocket 의 사용 여부를 확인하는 것과 같음
			// 사용시 serverSocket 은 널 값이 아니여야 하고 열려있어야 함으로 
			// 사용 후에 닫아주는 것 
			if(serverSocket != null && serverSocket.isClosed() == false) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	static final void log(String log){
		System.out.println("[나는야 서버] " + log);
	}
}
