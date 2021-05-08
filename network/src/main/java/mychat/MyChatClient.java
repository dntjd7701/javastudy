package mychat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class MyChatClient {

	public static void main(String[] args) {

		// 메세지를 입력해야되니까!
		Scanner sc = null;
		Socket socket = null;

		try {
			// 메세지를 보낼 Scanner 객체 생성
			sc = new Scanner(System.in);
			// 소켓 생성
			socket = new Socket();

			// 서버에 연결 !
			// 여기서 나의 로컬 주소 입력하고( 연결되어있어야 함으로 )
			// 서버에서 지정한 포트 번호 입력
			socket.connect(new InetSocketAddress("127.0.0.1", 5923));

			// 여기선 server -> client 로 소켓으로부터 데이터 읽기
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			// client -> server 로 소켓에 데이터 쓰
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			System.out.println("ID를 입력하세여 => ");
			String nickname = sc.nextLine();

			// server 와 약속한 지정된 형태로
			// 메세지를 적음, 이렇게 함으로써 서버는 doJoin 메소드를 실행할 수 있고,
			// 리스트에 현 소켓 정보를 저장하게 됌.
			pw.println("join:" + nickname);

			// Thread 시작 !

			new MyChatClientThread(nickname, br).start();

			// Server와 약속한 형태로 메세지를 입력할 수 있도록
			// 프로토콜을 지정하여 입력 !

			while (true) {
				System.out.println("[나는야 클라이언트] ");
				// 메세지 입력
				String input = sc.nextLine();

				if ("quit".equals(input)) {
					pw.println("quit");
				} else {
					/**
					 * 자 여기선, quit으로 입력한 메세지를 제외하곤 모두가 작성자가 작성하길 원하는 메세지 이므로 서버와 약속한 메세지의 형태에 맞게
					 * 작성하여 입력해준다.
					 * 
					 */
					pw.println("message:" + input);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 자원 정리 !
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
				if (sc != null) {
					sc.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
