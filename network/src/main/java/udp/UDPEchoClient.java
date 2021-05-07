package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import echo.EchoServer;

public class UDPEchoClient {

	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = UDPEchoServer.PORT;
	private static final int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		Scanner scanner = null;
		
		try {
			//1. Scanner 생성
			scanner = new Scanner(System.in);
			
			//2. 소켓 생성
			// 요놈은 읽는 놈이 아니라 보내는 놈이여서 그냥 적으면 
			socket = new DatagramSocket();
			
			while(true) {
				//3. 키보드 입력 받기
				System.out.print(">");
				String line = scanner.nextLine();
				
				if("exit".equals(line)) {
					break;
				}
				
				//4. 데이터 쓰기
				byte[] sendData = line.getBytes("UTF-8");
				DatagramPacket sendPaket = new DatagramPacket(
						sendData, 
						sendData.length, 
						new InetSocketAddress(SERVER_IP, SERVER_PORT));
				
				// 5. 데이터 보내기 
				socket.send(sendPaket);
				
				
				//6. 데이터 읽기
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(receivePacket);

				byte[] receiveData = receivePacket.getData();
				int length = receivePacket.getLength();
				String message = new String(receiveData, 0, length, "utf-8");

				//7. 콘솔 출력
				System.out.println("<" + message);
			}
			
		}  catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(scanner != null) {
				scanner.close();
			}
			if(socket != null && socket.isClosed() == false) {
				socket.close();
			}
		}
	}
}