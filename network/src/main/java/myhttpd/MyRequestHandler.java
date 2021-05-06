package myhttpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class MyRequestHandler extends Thread {
	private static final String DOCU = "./webapp";
	private Socket socket;

	public MyRequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		// get IOStream
		try {
			OutputStream os = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// logging Remost Host IP Address & Port

			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			consoleLog("connected from" + inetSocketAddress.getAddress().getHostAddress() + ":"
					+ inetSocketAddress.getPort());

			String request = null;

			while (true) {
				String line = br.readLine();
				// 브라우저가 연결을 끊으면,
				if (line == null) {
					break;
				}
//				if("".equals(line)) {
//					break;
//				}

//				if(request == null) {
//					request = line;
//					break;
//				}
			}

			// 요청 처리
			String[] tokens = request.split(" ");
			if ("GET".equals(tokens[0])) {
				consoleLog("request:" + tokens[1]);
				responseStaticResource(os, tokens[1], tokens[2]);
			} else {
				responese400Error(os, tokens[1], tokens[2]);
			}

		} catch (Exception e) {
			consoleLog("error:" + e);
		} finally {
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void responese400Error(OutputStream os, String url, String protocol) throws IOException {
		File file = new File(DOCU + "/error/400.html");
		if (file.exists() == false) {
			response404Error(os, url, protocol);
			return;
		}

		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		os.write((protocol + " 200 OK\r\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		os.write("\r\n".getBytes());
		os.write(body);

	}

	private void response404Error(OutputStream os, String url, String protocol) throws IOException, IOException {

		File file = new File(DOCU + "/error/404.html");
		if (file.exists() == false) {
			response404Error(os, url, protocol);
			return;
		}
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		os.write((protocol + " 200 OK\r\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		os.write("\r\n".getBytes());
		os.write(body);

	}

	private void responseStaticResource(OutputStream os, String url, String protocol) throws IOException {
		if ("/".equals(url)) {
			url = "/index.html";
		}
		File file = new File(DOCU + url);
		if (file.exists() == false) {
			response404Error(os, url, protocol);
			return;
		}

		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		os.write((protocol + " 200 OK\r\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		os.write("\r\n".getBytes());
		os.write(body);

	}

	public void consoleLog(String message) {
		System.out.println("[RequestHandler#" + getId() + "] " + message);
	}

}
