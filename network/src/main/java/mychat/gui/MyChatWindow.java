package mychat.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;

public class MyChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	private String nickname;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;

	public MyChatWindow(String nickname, Socket socket) {
		frame = new Frame(nickname);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);

		this.nickname = nickname;
		this.socket = socket;

	}

	public void show() {
		/**
		 * 1. UI 초기화
		 */
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				sendMessage();
			}
		});

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if (keyCode == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();

		/**
		 * 2. IOStream 생성
		 */
		try {
			 br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			 pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

			/**
			 * 3. Chat Client Thread 생성(Receive Thread)
			 */
			
			// welcome message
			updateTextArea(nickname + ", Welcome !!");
			
			
			// Thread 시작 
			Thread thread = new MyChatClientThread();
			thread.start();
			

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void finish() {
		System.exit(0);
		if(socket != null && !socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("[연결 실패 ?]");
			}
		}
	}

	private void sendMessage() {
		String message = textField.getText();
		// 서버 쓰레드에서 join일 경우에 doJoin메소드를 실행하고
		// 나머지의 경우에는 메세지를 출력하도록 했음으로
		// message만 보내도 괜찮 
		pw.println(message);
//		updateTextArea(nickname + ":" + message);
		textField.setText("");
		textField.requestFocus();
	}

	private void updateTextArea(String message) {

		textArea.append(message);
		textArea.append("\n");
	}

	public class MyChatClientThread extends Thread {

		@Override
		public void run() {
			while(true) {
				try {
					// receivemsg
					String receiveMsg = br.readLine();
					updateTextArea(receiveMsg);
				} catch(SocketException e) {
					System.out.println("[연결 끊김]" + e);
					break;
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}

	}
}
