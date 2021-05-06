package httpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	private static final String DOCUMENT_ROOT = "./webapp";
	private Socket socket;

	public RequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// get IOStream
			OutputStream os = socket.getOutputStream();
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
				if (line == null) {
					break;
				}

				// request header만 읽음
				if ("".equals(line)) {
					break;
				}
				// 첫 번째 라인만 처리
				if (request == null) {
					request = line;
					break;
				}
			}

			consoleLog(request);

			// 요청 처리
			String[] tokens = request.split(" ");
			if ("GET".equals(tokens[0])) {
				consoleLog("request:" + tokens[1]);
				responseStaticResource(os, tokens[1], tokens[2]);
			} else { // methods : POST, PUT, DELETE, HEAD, CONNECT
				// Simple_Http Server 에서는 무시 (우리꺼)
				System.out.println("!");
				
				
//				HTTP/1.1 400 Bad Request File Not Found OK\r\n
//				Content-Type:text/html; charset=utf-8\r\n
//				\r\n
//				HTML 에러 문서 (./webapp/error/400.html)

			response400Error(os,tokens[1],tokens[2]);
			}
			// 예제 응답입니다.
			// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
			// printwrite 보다는 이미지가 포함되어있기 때문에 바이트로 그냥 해버림

			
			//테스트 끝나면 주석 처리 ㄱ 
			// header
//			os.write("HTTP/1.1 200 OK\r\n".getBytes("UTF-8"));
//			os.write("Content-Type:text/html; charset=utf-8\r\n".getBytes("UTF-8"));
			// 구분 (header - body)
//			os.write("\r\n".getBytes());
//			os.write("<h1>이 페이지가 잘 보이면 실습과제 SimpleHttpServer를 시작할 준비가 된 것입니다.</h1>".getBytes("UTF-8"));

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

	private void response400Error(OutputStream os,String url,String protocol) throws IOException, IOException {
		File file = new File(DOCUMENT_ROOT + "/error/400.html");
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		os.write((protocol + "404 File Not Found OK\r\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		os.write("\r\n".getBytes());
		os.write(body);
		
	}
	private void responseStaticResource(OutputStream os, String url, String protocol) throws IOException{
		// welcome file set
		if("/".equals(url)) {
			url = "/index.html";
		}
		// file read & 존재 여부 확인 
		File file = new File(DOCUMENT_ROOT + url);
		if(file.exists() == false) {
			// 응답 예시
			File file01 = new File(DOCUMENT_ROOT + "/error/404.html");
			byte[] body = Files.readAllBytes(file01.toPath());
			String contentType = Files.probeContentType(file01.toPath());
			os.write((protocol + "404 File Not Found OK\r\n").getBytes("UTF-8"));
			os.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
			os.write("\r\n".getBytes());
			os.write(body);
			
//			HTTP/1.1 404 File Not Found OK\r\n
//			Content-Type:text/html; charset=utf-8\r\n
//			\r\n
//			HTML 에러 문서 (./webapp/error/404.html)

//			response404Error(os, url, protocol);
			return;
		}
		//FileInputStreamReader 대신 nio 사용 
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
//		String contentType = Files.probeContentType(file.toPath());
		os.write((protocol + " 200 OK\r\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		os.write("\r\n".getBytes());
		os.write(body);
	}

	public void consoleLog(String message) {
		System.out.println("[RequestHandler#" + getId() + "] " + message);
	}
}
