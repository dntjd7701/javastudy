package chat.gui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientApp {
	
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 6910;
	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String name = null;
		Socket socket = null;
		
		while( true ) {
			
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = scanner.nextLine();
			
			if (name.isEmpty() == false ) {
				break;
			}
			
			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}
		
		scanner.close();
		
		try {
			// 1. create socket
			socket = new Socket();
			// 2. connect to server
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			
			// 3. crate iostream
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);
			
			pw.println("JOIN:"+name);
			// 4. join
			String line = br.readLine();
		
			if("JOIN:OK".equals(line)) {
				new ChatWindow(name, socket, br, pw).show();
			}
			
			
		} catch (ConnectException e) {
			System.out.println(name + "님 죄송합니다 \n서버 연결에 실패 했습니다. (서버가 꺼져있을 가능성 농후함)");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}