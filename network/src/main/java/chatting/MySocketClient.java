package chatting;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySocketClient {
	private static final int PORT = 1234;
	public static void main(String[] args) {

		
		try {
			Socket socket = null;
			socket = new Socket("127.0.0.1", PORT);
			System.out.println("서버 접속 성공!!!!");
			//접속 확인
			
			
			//서버에서 보낸 메세지 읽기 
			ListenThread lthread = new ListenThread(socket);
			//서버로 메세지 보내는 thread
			WriteThread wthread = new WriteThread(socket);
			
			
			lthread.start();
			wthread.start();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
