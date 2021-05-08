package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ChatServerThread extends Thread{
	
	private String nickname;
	private Socket socket;
	private List<Writer> listWriters;

	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		PrintWriter pw = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"), true);
			
			while( true ) {
				String request = br.readLine();
//				if(request == null) {
//					log("클라이언트로 부터 연결 끊김");
//					doQuit(pw);
//					break;
//				}
			
				String tokens [] = request.split(":");
				if( "join".equals(tokens[0])) {
					doJoin( tokens[1] , pw);
				} else if( "message".equals(tokens[0])) {
					doMessage( tokens[1] );
				}else if("quit".equals(tokens[0])) {
					doQuit(pw);
				}else {
					ChatServer.log( "에러: 알수 없는 요청 ("+tokens[0] +")");
				}
			}
		} catch (SocketException e) {
			log("클라이언트로 부터 연결 끊김");
			doQuit(pw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void doJoin(String nickname, PrintWriter pw) {
		this.nickname = nickname;
		
		String data = nickname + "님이 참여하였습니다.";
		broadcast(data);
		/* writer pool에 저장 */
		addWriter(pw);
		// ack
		pw.println("join:ok");
		
	}
	
	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}
	
	private void broadcast(String data) {
		synchronized (listWriters) {
			for(Writer writer: listWriters) {
				PrintWriter pw = new PrintWriter(writer,true);
				pw.println(data);
			}
		}
	}

	private void doMessage(String data) {
		broadcast(data);
	}
	
	private void doQuit(Writer writer) {
		removeWriter(writer);
		
		String data = nickname + "님이 퇴장하였습니다.";
		broadcast(data);
	}

	private void removeWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.remove(writer);
		}
	}
	
	private static final void log(String log) {
		System.out.println(log);
	}
}