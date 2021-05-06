package socketthread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocketServer {

	private static final int PORT = 2453;
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT); // 소켓 만들
			Socket socket = null; //클라이언트 접속시 이용할 소
			System.out.println("socket : " + PORT + " 으로 서버가 열림 !!!");
			
			// 소켓 서버가 종료될 때까지 반
			while(true) {
				socket = serverSocket.accept(); //소켓 서버로 접속 시 socket에 접속자 정보 할
				// 접속자의 getLocalAddress 가져오
				System.out.println("Client 접속 완료!! " + socket.getLocalAddress() + "/" + socket.getLocalSocketAddress() + "/" + socket.getPort());
				
				
				//클라이언트에서 서버로 
				InputStream is = socket.getInputStream(); //socket의 InputStream 정보를 in에 넣음
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				
				
				// 메세지 확인
				System.out.println("클라이언트에서 보낸 메시지 입니당 : " + br.readLine());

				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
				
				pw.println("저쪽의 서버님께서 보내셨습니다.");
				pw.println("mr.client. good to see you");
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
