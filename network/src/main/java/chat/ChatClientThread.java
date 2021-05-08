package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;

public class ChatClientThread extends Thread{

	private String myNickname = null;
	private BufferedReader br= null;
	
	public ChatClientThread(String nickname,BufferedReader br) {
		myNickname = nickname;
		this.br = br;
	}

	@Override
	public void run() {
		try {
			while(true) {

				String data = br.readLine();
//				if( data == null ) {
//					ChatClient.log("closed by server");
//					break;
//				}
				
				if("join:ok".equals(data)) {
					System.out.println(myNickname + "님 환영합니다.");
					System.out.print(">>");
				}else {
					System.out.println("<" + data);
					System.out.print(">>");
				}
				
			}
		} catch (SocketException e) {
			ChatClient.log("closed by server");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(br != null )
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
}
