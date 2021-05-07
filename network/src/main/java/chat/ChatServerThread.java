package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ChatServerThread extends Thread {

	// 유저 확인용 리스트 !
	private String nickname;
	private Socket socket = null;
	private BufferedReader br;
	private PrintWriter pw;
	List<Writer> listwriters;

	public ChatServerThread(Socket socket, List<Writer> listwriters) {
		// 유저 소켓 할당 
		this.socket = socket;
		
		this.listwriters = listwriters;
	}

	@Override
	public void run() {

		// 1.Remot host Information
		InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
		String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
		int remoteHostPort = inetRemoteSocketAddress.getPort();
		ChatServer.log("클라이언트 접속 완료. [" + remoteHostAddress + ":" + remoteHostPort + "]");

		
		try

		{	
			// 2. Stream 얻기 
			
			// br 클라이언트에서 보낸 메시지 읽기 
			 br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			 // pw 서버에서 클라이언트로 메세지 보내기 
			 pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			 
			 
			 // 클라이언트에게 서버 연결 완료 메세지 보내기 
			 pw.println("서버에 연결 완료 !! ");
			 pw.println("닉네임을 고고 !");

			 
			 
			// 3. 요청 처리
			while(true) {
				// 4. 데이터 읽기
				String data = br.readLine();
				
				// 연결 확인
				// 에러 처리,클라이언트가 quit 보내지 않고 소켓을 닫은 경
				if(data == null) {
					ChatServer.log("클라이언트로부터 연결이 끊겼습니다 !!!");
					doQuit(pw);
					break;
				}

				
				// 5. 데이터 분석 
				String[] tokens = data.split(":");
				pw.println("Check");
				
				// 데이터 처리
				if("join".equals(tokens[0])) {
					doJoin(tokens[1], pw);
				} else if("message".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if("quit".equals(tokens[0])) {
					doQuit(pw);
				} else {
					ChatServer.log("고장남:알 수 없는 요청(" + tokens[0] + ")");
				}
				 
				// 5. 데이터 쓰기 
				pw.println(data);
			}
			
			
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
	}
	
	
	
	
	
	
	
	private void doJoin(String nickname, Writer writer) {
		this.nickname = nickname;
		
		// 입장 시 메시지 브로드캐스팅 !
		String data = nickname + "님이 참여하였습니다.";
		broadcast(data);
		
		// writer pool에 저장 
		addWriter(writer);
		
		//ack
		// ack 수정해봄 
		pw.println("join:Successs!!!!!!!");
		
	}
	private void addWriter(Writer writer) {
		synchronized(listwriters) {
			listwriters.add(writer);
		}
	}
	
	
	
	
	private void broadcast(String data) {
		synchronized (listwriters) {
			for(Writer writer : listwriters) {
				PrintWriter pw = (PrintWriter) writer;
				pw.println(data);
			}
			
		}
	}
	
	
	
	
	
	// 메시지 고고 
	private void doMessage(String message) {
		String data = nickname + ":" + message;
		broadcast(data);
		
		//ack
		pw.println(data);
	}
	
	
	
	
	
	
	
	private void doQuit(Writer writer) {
		// 메세지 출력하기 
		String data = nickname + "님이 퇴장 하였습니다.";
		broadcast(data);

		//ack 보내기 
		pw.println("quit:" + nickname);
		
		// 풀에서 삭제하기 
		removeWriter(writer);
						
		
	}
	
	private void removeWriter(Writer writer) {
		synchronized (listwriters) {
			listwriters.remove(writer);
		}
		
	}
	
	
	
	
}
