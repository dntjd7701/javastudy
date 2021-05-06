package myecho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MyEchoServerThread extends Thread {

	private Socket socket;
	
	public MyEchoServerThread(Socket socket) {
		this.socket = socket;
		
	}

	@Override
	public void run() {
		InetSocketAddress inetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
		String remoteHostAddress = inetSocketAddress.getAddress().getHostAddress();
		int remoteHostPort = inetSocketAddress.getPort();
		MyEchoServer.log("연결 완료 by client [" + remoteHostAddress + "--" + remoteHostPort + "]");
	
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			
			
			while(true) {
				String data = br.readLine();
				if(data == null) {
					MyEchoServer.log("closed by client");
					break;
				}
				
				MyEchoServer.log(data);
				
				pw.println(data);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
			if(socket != null && socket.isClosed() == false) {
					socket.close();
			}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		
		
	}

	
}
