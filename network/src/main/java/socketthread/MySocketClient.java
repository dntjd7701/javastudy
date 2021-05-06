package socketthread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySocketClient {

	public static void main(String[] args) {

		try {
			Socket socket = new Socket("127.0.0.1", 2453);
			System.out.println("socket 서버에 접속 성공 !");
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			
			pw.println("안녕하세요 클라이언트라고 합니다. 만나서 반갑습니다. ");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			System.out.println("서버에서 온 메시지 임다 !!" + br.readLine());
			System.out.println("종료합니다.");
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //소켓 서버에 접
		
	
		
	}

}
