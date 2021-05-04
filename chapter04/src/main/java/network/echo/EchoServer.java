package network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	private static final int PORT = 7000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(Binding)
			// Socket에 InetSocketAddress(IPAddress + Port)
			// IPAddress : 0.0.0.0 : 모든 IP 연결 허용
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("strats.....[port: " + PORT + "]");

			// 3. accept
			// 클라이언트의 연결 요청을 기다린다.
			Socket socket = serverSocket.accept(); // blocking

			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetRemoteSocketAddress.getPort();
			log("[server] connected by client[" + remoteHostAddress + ":" + remoteHostPort + "]");

			try {
				// 4. IO Stream 받아오기
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
				// BufferedReader 반대놈, 안에 버퍼가 있음
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

				while (true) {
					// 4. 데이터 읽기

					String data = br.readLine();
					if (data == null) {
						log("closed by client");
						break;
					}
					log("received:" + data);
					// 5. 데이터 쓰기
					pw.println(data);
				}
			} catch (SocketException e) {
				log("[server] suddenly closed by client");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null && socket.isClosed() == false) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (

		IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void log(String log) {
		System.out.println("[EchoServer] " + log);
	}
}
