package mychat;

import java.io.BufferedReader;
import java.io.IOException;

public class MyChatClientThread extends Thread {
	private String nickname = null;

	// 우리가 적은 메세지를 소켓을 통해 서버가 받고,
	// 그 받은 메세지를 모든 클라이언트에게 다시 보내줌으로 ( echo ++)
	// 이 쓰레드에선 받는 역할\
	// 즉 메세지를 받아서 볼 수 있는 역할을 해야 한다.
	private BufferedReader br = null;

	public MyChatClientThread(String nickname, BufferedReader br) {
		this.nickname = nickname;
		this.br = br;

	}

	@Override
	public void run() {
		try {
			while (true) {
				String messages = br.readLine();
				if (messages == null) {
					log("Closed by Server");
					break;
				}

				// 자, TCP 에서 ACK를 보냈다.
				// join 이 성공했을 경우 join이라는 메세지를 보낼 것이다.
				// join 이 들어오게 되면, 아래와 같이 환영 인사를 한번 보여주고,
				// 다음부턴 채팅을 시작할 수 있게 하면 된다.
				if ("join".equals(messages)) {
					System.out.println(nickname + ", Welcome !");
					System.out.print("# ");
				} else {
					// 내가 입력한 데이터(메세지) (에코같이)
					System.out.println("## " + messages);
					// 새로운 작성 
					System.out.print("$$ ");
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static final void log(String log) {
		System.out.println("[나는야 클라이언트 쓰레드] ");

	}

}
