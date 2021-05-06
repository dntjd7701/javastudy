package httpd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RequestHandler extends Thread {
	private Socket socket;

	public RequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// get IOStream
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

			// client 의 브라우저가 돌고있는 호스트의 ip, 포트 번호
			consoleLog("connected from " + inetSocketAddress.getAddress().getHostAddress() + ":"
					+ inetSocketAddress.getPort());
			
			String request = null;
			while (true) {
				// 요청을 읽고, 아래에서 응답을 보내
				String line = br.readLine();
				// 브라웆거ㅏ 연결을 끊으면, 
				if(line == null) {
					break;
				}
				
				// request header만 읽음
				if("".equals(line)) {
					break;
				}
				// 첫 번째 라인만 처리 
				if(request == null) {
					request = line;
					break;
				}
			}
			consoleLog(request);
			// 예제 응답입니다.
			// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
			// printwrite 보다는 이미지가 포함되어있기 때문에 바이트로 그냥 해버림

			// header
			outputStream.write("HTTP/1.1 200 OK\r\n".getBytes("UTF-8"));
			outputStream.write("Content-Type:text/html; charset=utf-8\r\n".getBytes("UTF-8"));
			// 구분 (header - body)
			outputStream.write("\r\n".getBytes());
			outputStream.write("<h1>이 페이지가 잘 보이면 실습과제 SimpleHttpServer를 시작할 준비가 된 것입니다.</h1>".getBytes("UTF-8"));

		} catch (Exception ex) {
			consoleLog("error:" + ex);
		} finally {
			// clean-up
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}

			} catch (IOException ex) {
				consoleLog("error:" + ex);
			}
		}
	}

	public void consoleLog(String message) {
		System.out.println("[RequestHandler#" + getId() + "] " + message);
	}
}
