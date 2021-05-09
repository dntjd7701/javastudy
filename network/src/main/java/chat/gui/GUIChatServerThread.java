package chat.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class GUIChatServerThread extends Thread{

	private String nickname;
	private Socket socket;
	private List<PrintWriter> listWriters;
	private BufferedReader br;
	private PrintWriter pw;
	
	public GUIChatServerThread(Socket socket, List<PrintWriter> listWriters) throws IOException {
		this.socket = socket;
		this.listWriters = listWriters;
		br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
		pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);
	}

	@Override
	public void run() {
		try {
			while(true) {
				String requestMsg = br.readLine(); // socketException
				String tokens [] = requestMsg.split(":");
				
				switch(tokens[0]) {
					case "JOIN" : doJoin(tokens[1]);
							break;
					default : broadcast(nickname+" : "+tokens[0]);
						break;
				}
			}
		} catch (SocketException e) {
			doQuit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null && !socket.isClosed())
					socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private void doJoin(String username) {
		nickname = username;
		String messsage = username +"님이 입장했습니다.";
		broadcast(messsage);
		addWriter();
		pw.println("JOIN:OK");
	}

	private void doQuit() {
		String message = nickname + "님이 퇴장했습니다.";
		broadcast(message);
		removeWriter();
	}
	private void broadcast(String message) {
		synchronized (listWriters) {
			for(PrintWriter writer : listWriters) {
				writer.println(message);
			}
		}
	}

	private void addWriter() {
		synchronized (listWriters) {
			listWriters.add(pw);
		}
	}
	
	private void removeWriter() {
		synchronized (listWriters) {
			listWriters.remove(pw);
		}
	}
}
