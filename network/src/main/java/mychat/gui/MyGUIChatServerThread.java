package mychat.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class MyGUIChatServerThread extends Thread {

	private Socket socket = null;
	private ArrayList<PrintWriter> listWriters;
	private BufferedReader br;
	private PrintWriter pw;
	
	private String nickname;

	public MyGUIChatServerThread(Socket socket, ArrayList<PrintWriter> listWriters)  throws IOException{
		this.socket = socket;
		this.listWriters = listWriters;
		 br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		 pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"), true);
		
	}
	
	Runnable run = new Thread();


	@Override
	public void run() {
		while(true) {
			
			try {
				String request = br.readLine();
				String[] tokens = request.split(":");
				
				if("JOIN".equals(tokens[0])) {
					doJoin(tokens[1]);
				} else {
					// 메세지 바로 출력 
					broadcast(nickname + ":" + tokens[0]);
				}
			} catch(SocketException e) {
				doQuit();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	
	}


	private void doQuit() {
		String msg = nickname + "님이 퇴장함 ";
		broadcast(msg);
		removeWriter();
	}


	private void removeWriter() {
		synchronized (listWriters) {
			listWriters.remove(pw);
		}
	}


	private void doJoin(String nickname) {
		this.nickname = nickname;
		String msg = nickname + "님이 입장함 ";
		broadcast(msg);
		addWriter();
		pw.println("JOIN:OK");
		
	}


	private void addWriter() {
		synchronized (listWriters) {
			listWriters.add(pw);
		}
		
	}


	private void broadcast(String msg) {
		synchronized (listWriters) {
			for(PrintWriter writer : listWriters) {
				writer.println(msg);
			}
		}
	}
	
	
	
}
