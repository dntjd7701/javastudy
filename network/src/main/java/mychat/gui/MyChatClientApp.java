package mychat.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class MyChatClientApp {

	// 연결할 서버 아이피, 서버 포트 설정
	private static final String SEVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 1231;

	public static void main(String[] args) {

		// 닉네임을 입력받을 변수 name
		String nickname = null;
		// 닉네임 입력받기.
		Scanner scanner = new Scanner(System.in);
		// 소켓 초기화
		Socket socket = null;

		while (true) {
			// 클라이언트 실행 시 닉네임을 설정
			System.out.println("Welcome ~!! 닉네임을 설정하세요 !!!");
			System.out.print("--> ");
			nickname = scanner.nextLine();

			if (nickname.isEmpty() == false) {
				break;
			}
			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}

		scanner.close();

		try {
			// 1. create socket
			socket = new Socket();

			// 2. connect to server
			socket.connect(new InetSocketAddress(SEVER_IP, SERVER_PORT));

			// 3. create iostream
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

			// 4. JOIN message 보내고,
			// 서버측과 미리 약속한 프로토콜 실행
			// 닉네임 저장 및 뿌려줘야하기 때문에
			// 닉네임도 추가로 함께 보냄
			pw.println("JOIN:" + nickname);

			// line 은 아래와 같은 메세지를 받을 것이고( from sever)
			// 메세지를 검사하여 (ACK)맞다면 MyChatWindow 실행
//			String line = "JOIN:OK";
			String line = br.readLine();

			// serverThread 로부터 ACK JOIN:OK를 받으면 윈도우 창 실행

			if ("JOIN:OK".equals(line)) {
				new MyChatWindow(nickname, socket).show();
			}
		} catch (ConnectException e) {
			System.out.println("서버를 열어주세요 !");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
