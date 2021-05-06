package httpd;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
	private static final int PORT = 8989;

	public static void main(String[] args) {

		ServerSocket serverSocket = null;

		try {
			// 1. Create Server Socket
			serverSocket = new ServerSocket();
			   
			// 2. Bind
			String localhost = InetAddress.getLocalHost().getHostAddress();
			
			// 로컬 -> 모든걸 다 받을 수 있도록 0.0.0.0 으로 수정 
			serverSocket.bind( new InetSocketAddress( "0.0.0.0", PORT ) );
			consolLog("starts....  [port:" + PORT + "]");

			while (true) {
				// 3. Wait for connecting ( accept )
				// 요청을 기다렸다가 받으면 아래로 
				Socket socket = serverSocket.accept();

				// 4. Delegate Processing Request
				// 위임하는 것. 디자인 패턴에 나옴. uml관계, 내가 처리하지 않고 넘기는 것 
				new RequestHandler(socket).start();
			}

		} catch (IOException ex) {
			consolLog("error:" + ex);
		} finally {
			// 5. 자원정리
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException ex) {
				consolLog("error:" + ex);
			}
		}
	}

	// main의 thread id를 구하는 것.
	// message 도 출력 
	public static void consolLog(String message) {
		System.out.println("[HttpServer#" + Thread.currentThread().getId()  + "] " + message);
	}
}
