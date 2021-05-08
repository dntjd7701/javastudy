package mychat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.List;

public class MyChatServerThread extends Thread {
	private Socket socket;
	private List<Writer> listWriters;

	// 클라이언트가 지정한 아이디를 저장할 변수
	private String nickname = null;
	// 생성자 생성
	// 파라미터로 받은 인자값 저장
	// 각각 소켓의 정보를 담고 있는 socket 변수와, ArrayList 객체 변수를 담고 있음.

	public MyChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	// Thread 클래스로부터 run 메소드 오버라이드
	@Override
	public void run() {
		// PrintWriter 변수 생성
		// 여기선 sever에서 client 로 메세지 보내는 것
		PrintWriter pw = null;

		try {
			// client로부터 받은 메세지를 BufferedReader 변수에 저장, 받은 데이터는 byte->char->String
			// 의 과정을 거쳐 String 형태로 저장됌.
			// socket.getInputStream 을 통해 먼저 byte로 받은 데이터를 인자로 넘기는 것이 시작
			// utf-8 형식으로 encoding
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// 여기선 server -> client
			// 즉, 보내는 데이터
			// 소켓에 보낼 데이터를 String->char->byte 형태로 보냄
			// unencoding 형식 지정
			// true 로 설정할 시, flush를 별도로 지정하지 않아도
			// 버퍼에 차면 보냄, \r\n이 기준
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			while (true) {
				// client로부터 받은 요청(데이터, 메시지)를 String 변수에 저장.
				String request = br.readLine();

				// request 값이 null, 즉 소켓 연결이 끊겼을 때에는 종료될 수 있도록 작성
				if (request == null) {
					log("Closed by Client");
//					doQuit(pw);
					break;
				}

				// String 배열 저장
				// 메세지 값을 나눠서 저장하여 그에 맞는 프로토콜을 진행하기 위해서임
				// request로 저장한 클라이언트의 메세지를
				// 임의로 지정한 ":"를 통해 나누어 각 토근들로 배열에 저장

				// client로부터 형식에 맞는 데이터를 받아야함 !
				String[] tokens = request.split(":");

				// 각 메세지를 분리하여 그에 맞는 프로토콜 시작
				if ("join".equals(tokens[0])) {
					// 구분된 메세지의 첫 번째 단어가 join 일시 doJoin 메소드 실행
					// 클라이언트가 보내는 메세지의 지정된 형태에서
					// join 메세지 다음으로는 클라이언트의 아이디가 들어옴으로,
					// 아이디 값인 tokens[1]과 클라이언트로 메세지를 보내기 위한
					// PrintWriter 객체의 변수 pw를 인자로 보냄.
					doJoin(tokens[1], pw);
				} else if ("message".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if ("quit".equals(tokens[0])) {
					doQuit(pw);
				} else {
					log("Error : join,message,quit 이 아님 !! (" + tokens[0]+")" );
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//자원 정리 
			if(socket != null && socket.isClosed() == false) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
	}

	private void doJoin(String nickname, PrintWriter pw) {
		this.nickname = nickname;
		// 클라이언트에게 보낼 메세지를 변수에 저장
		// pw는 String 의 형태로 데이터를 받아 byte로 마지막에 전송되므로
		// String 값으로 보내야함
		String textToClinet = nickname + "가 참여함 !!!!";
		// 클라이언트,즉, 작성자 저장
		addWriter(pw);

		// TCP통신의 ack 전송
		// client에게 client가 참여했음을 알리는 join,
		// 이걸 시그널로 채팅을 시작하게 하면 된다.
		pw.println("join");

	}

	private void addWriter(PrintWriter pw) {
		/**
		 * 중요한 부분이다. 각 클라이언트가 가진 소켓, 힙에 저장된 고유한 소켓의 정보를 가지고 있는 pw변수를 인자로 받고 리스트에 저장할
		 * 것이다. 또한 저장한 이 리스트를 동기화 함으로써 중간으로부터의 개입을 막고 같은 데이터를 볼 수 있도록 한다.
		 *
		 */
		synchronized (listWriters) {
			// 여기서 pw는 각 클라이언트들, 참가한 작성자들, writer를 의미한다고 할 수 있다.
			listWriters.add(pw);
		}

	}

	private void doMessage(String message) {
		broadcast(message);
	}

	private void broadcast(String message) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {

				/**
				 * 자, 여기의 message에는 클라이언트로부터 받은 message 가 저장되어 있다. 또한 리스트 변수 listWriters에는 각각의
				 * 소켓 정보(각 클라이언트들의 소켓)를 담고있고 이 모든 것들을 for each문을 통해 모두 꺼낼 것이다.
				 * 
				 * 메세지를 소켓에 적기 위해 PrintWriter 객체를 생성하고, 그 인자 값으로 리스트로부터 뺀 각 클라이언트들의 소켓 정보, 각각의
				 * 소켓 정보를 넣는다. 이 새로운 pw를 이용하여 각자에게 메세지를 모두 전송하는 broadcast 역할을 수행한다.
				 * 
				 */
				PrintWriter pw = new PrintWriter(writer, true);
				pw.println(message);
			}
		}
	}

	private void doQuit(PrintWriter pw) {
		removeWriter(pw);
		
		String textToClient = nickname + "님이 나감!!!";
		pw.println(textToClient);
		
	}

	private void removeWriter(PrintWriter pw) {
		synchronized (listWriters) {
			listWriters.remove(pw);
		}
		
	}

	static final void log(String log) {
		System.out.println("[나는야 서버 쓰레드] ");
	}

}
