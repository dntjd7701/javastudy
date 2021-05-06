package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;

public class MySocketServerThread extends Thread {
	// 유저 확인용 리스트
	static ArrayList<Socket> list = new ArrayList<Socket>();
	static Socket socket = null;

	public MySocketServerThread(Socket socket) {
		// 유저 socket을 할당
		this.socket = socket;
		// 유저를 list에 추가
		list.add(socket);
	}

	@Override
	public void run() {
		// Thread 에서 start() method 사용 시 자동으로 해당 메소드가 시작된다.
		// Thread 별로 개별적으로 수행된다.
		
		
		
		try {
			// InputStream - 클라이언트에서 보낸 메시지 읽기
			System.out.println("서버 : " + socket.getInetAddress() + "IP의 클라이언트와 연결되었습니다.");
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			//OutputStream - 서버에서 클라이언트로 메시지 보내기
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			
			// 클라이언트에게 연결되었다는 메세지 보내기
			pw.println("서버에 연결됨!!!! ID 입력하세여 !");
			
			String readValue; //클라이트에서 보낸 값 저
			String name = null; // 클라이언트 이름 설정
			boolean identify = false;
			
			// 클라이언트가 메세지 입력시마다 수행
			while((readValue = br.readLine()) != null) {
				if(!identify) {// 연결 후 한번만 노
					name = readValue;
					identify = true;
					pw.println(name + "님이 접속함 !");
					continue;
				}
				// list 안에 클라이언트 정보가 담겨있
				for(int i = 0; i < list.size(); i++) {
					os = list.get(i).getOutputStream();
					pw = new PrintWriter(os, true);
					// 클라이언트에게 메세지 발송
					
					pw.println(name + ":" + readValue);
					

				}
			}
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
