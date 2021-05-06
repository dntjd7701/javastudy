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

	private String nickname;
	private Socket socket;
	private static List<Writer> listWriters;
	private BufferedReader br;
	private PrintWriter pw;

	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.listWriters = listWriters;
		this.socket = socket;
	}

	@Override
	public void run() {

		// 1.Remot host Information

		InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
		String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
		int remoteHostPort = inetRemoteSocketAddress.getPort();
		ChatServer.log("CONNECTED BY CLIENT[" + remoteHostAddress + ":" + remoteHostPort + "]");

		// 2. stream 얻기
		try

		{
			 br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			 pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			// 3. 요청 처리
			while(true) {
				// 4. 데이터 읽기
				String data = br.readLine();
				if(data == null) {
					ChatServer.log("클라이언트로부터 연결이 끊겼습니다 !!!");
					break;
				}
				
				ChatServer.log("수:" + data);
				//data ?
				String[] tokens = data.split( ":" );
				
				// 확인
				for(int i = 0; i < tokens.length; i++) {
					System.out.println("토큰 : " + tokens[i]);
					
				}
				if("join".equals(tokens[0])) {
					doJoin(tokens[1], pw);
				} else if("message".equals(tokens[0])) {
					doMessage(tokens[1] + ":" + tokens[2]);
				} else if("quit".equals(tokens[0])) {
					doQuit(tokens[1], pw);
				} else {
					ChatServer.log("ERROR:UNKNOWN REQUEST(" + tokens[0] + ")");
				}
				 
				// 5. 데이터 쓰기 
				pw.println(data);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} //catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (socket != null && socket.isClosed() == false) {
//					socket.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	}
	}
	
	private void doJoin(String nickname, Writer writer) {
		this.nickname = nickname;
		
		String data = nickname + "님이 참여하였습니다.";
		broadcast(data);
		
		// writer pool에 저장 
		addWriter(writer);
		
		//ack
		pw.println("join:" + data);
		
	}

	private void addWriter(Writer writer) {
		synchronized(listWriters) {
			listWriters.add(writer);
		}
	}
	
	private void removeWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.remove(writer);
		}
		
	}
	
	private void doMessage(String message) {
		String data = nickname + ":" + message;
		broadcast(data);
		
	}
	
	private void doQuit(String nickname, Writer writer) {
		removeWriter(writer);
		
		String data = nickname + "님이 퇴장 하였습니다.";
		broadcast(data);
		//ack
		listWriters.remove(writer);
		
	}
	
	
	private void broadcast(String data) {
		synchronized (listWriters) {
			for(Writer writer : listWriters) {
				PrintWriter pw = (PrintWriter)writer;
				System.out.println(data);
				pw.println(data);
//				printWriter.flush();
			}
			
		}
	}
	
	
}
