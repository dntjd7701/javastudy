package mynetworkpractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import network.test.echo.EchoServer;

public class MyEchoClient {
	private static final String SERVER_IP = "192.168.254.5";
	private static final int SERVER_PORT = EchoServer.PORT;

	public static void main(String[] args) {

		Socket socket = null;
		Scanner scanner = null;

		try {
			scanner = new Scanner(System.in);

			// 2. 소켓을 생성 / 서버에 접속 요청을 보냄
			socket = new Socket();

			// 3. 서버에 연결을 함
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			Log("connected");

			// 4. IOStream 받아오기
			// server 와 client 가 서로 주고 받은 데이터를 서로 출력하고 보내야 함으로
			// 보내는 것에 BufferedWriter
			// 출력하는 것에 BufferedReader
			// 를 사용한다.

			// char형을 String 형태로 뽑아오는 것과 같다
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// 여기서 true 값을 하게 되면,버퍼에 데이터를 쌓아두지 않고 한번에 출력하게 된다.
			// 메소드 println, print을 쓸 때 ㅇㅇ

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			while (true) {
				// 5. 키보드로 입력 받기
				// 메시지

				System.out.print(" >>>>> ");
				String line = scanner.nextLine();

				// "exit" 값이 들어오면 무한 루프를 종료한다.
				if ("exit".equals(line)) {
					break;
				}

				// 6. 데이터 쓰기
				// PrintWriter의 메소드
				// print할 데이터 뒤에 /r/n을 추가하여, 데이터 와 함께 개행을 출력한다.
				// 객체를 생성할 때 "true" 값을 주었으므로
				// 버퍼에 쌓아두지 않고 개행과 함께 바로 출력한다.
				// 즉, 개행 문자를 기준으로 나눠 출력하는 것과 같다.
				// print로 실행시 뒤에 개행 문자를 추가하여 입력을 구분할 수 있다.
				pw.println(line);

				// 7. 데이터 읽기
				// 문자열을 라인별로 읽어들인다.
				// 개행문자를 기준으로 나눈다.
				String data = br.readLine();

				if (data == null) {
					Log("closed by server");
					break;
				}

				// 8. 콘솔로 출력한다.

				System.out.println("<<<<<< " + data);

			}

		} catch (SocketException e) {
			Log("suddenly closed by server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (scanner != null) {
					scanner.close();
				}
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void Log(String log) {
		System.out.println("[EchoClient] " + log);
	}

}
