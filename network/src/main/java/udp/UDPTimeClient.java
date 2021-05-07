package udp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UDPTimeClient {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 5312;

	public static void main(String[] args) {

		DatagramSocket socket = null;
		// 1. 소켓 생성 
		try {
			socket = new DatagramSocket();
			
			
			// 2. 데이터 쓰기
			
			byte[] sendData = "".getBytes("UTF-8");
			DatagramPacket sendPacket = new DatagramPacket(
					sendData,
					sendData.length,
					new InetSocketAddress(SERVER_IP ,SERVER_PORT));
			
			// 3. 데이터 보내기
			socket.send(sendPacket);
			
			// 4. 데이터 읽기 (날짜 데이터 받기)
			
			
			DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
			socket.receive(receivePacket);
			
			byte[] receiveData = receivePacket.getData();
			int length = receivePacket.getLength();
			String date = new String(receiveData, 0, length, "utf-8");
			
			// 5. 출력
			
			System.out.println("[받은 날짜] : " + date);
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(socket != null && socket.isClosed() == false) {
				socket.close();
			}
		}
		
	}

}
