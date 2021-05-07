package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPTimeServer {
	
	public static void main(String[] args) {
		DatagramSocket socket = null;

		try {
			// 1. 소켓 생성 
			socket = new DatagramSocket(5312);
			
				// 2. 데이터 수신
				DatagramPacket receivePacket = new DatagramPacket(new byte[0], 0);
				socket.receive(receivePacket);
				
				

				// 3. 데이터 쓰기 
				String now = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
				byte[] sendData = now.getBytes("utf-8");
				DatagramPacket sendPacket = new DatagramPacket(
						sendData, 
						sendData.length, 
						receivePacket.getAddress(),
						receivePacket.getPort());
						
						
				// 4. 데이터 송신 
				socket.send(sendPacket);
				
				
				
				
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
