package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListenThread extends Thread {
	Socket socket = null;

	public ListenThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (true) {

				System.out.println(br.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
