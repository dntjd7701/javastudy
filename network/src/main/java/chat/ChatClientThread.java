package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClientThread extends Thread {
	private BufferedReader br;

	Socket socket = null;

	public ChatClientThread(Socket socket) {
		this.socket = socket;

	}

	@Override
	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (true) {

				System.out.println(br.readLine());

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
