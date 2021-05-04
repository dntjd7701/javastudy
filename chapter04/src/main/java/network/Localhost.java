package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Localhost {

	public static void main(String[] args) {

	
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			
			String hostname = inetAddress.getHostName();
			String hostaddress = inetAddress.getHostAddress();
			
			System.out.println(hostname);
			System.out.println(hostaddress);
			
			
			byte[] addresses = inetAddress.getAddress();
			
			for(byte address : addresses) {
				System.out.print((int)address & 0x000000ff);
				System.out.print(".");
				
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
