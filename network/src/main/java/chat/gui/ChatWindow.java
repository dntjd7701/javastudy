package chat.gui;

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

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	// 소켓 추가 ?
	public ChatWindow(String nickname) {
		frame = new Frame(nickname);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {
		/**
		 * 1. UI 초기화. 건들지 마!!
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
				// 요게 맞으면 enter를 친거
				if (keyCode == KeyEvent.VK_ENTER) {
					// 이 때 sendMessage

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
		// Don't touch !
		frame.addWindowListener(new WindowAdapter() {
			// 바로 이게 끝내는거
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();

		/**
		 * 2. IOStream 생성 (코드 만들기)
		 */

		/**
		 * 3. ChatClientThread 생성 (Receive Thread)
		 */

	}
	private void finish() {
		// 여기서 방 나가기 프로토콜을 구현해야돼 
		// 소켓을 닫으면 방나가기가 되는거야
		// 두 개를 다 구현하는게 아니고, 선택해서 구현하는거야 
		System.out.println("방나가기 프로토콜 구현 ");
		System.out.println("소켓 닫기");
		System.exit(0);
	}

	private void sendMessage() {
		String message = textField.getText();

		// 프로 토콜 구현. enter 치는 거
		System.out.println("프로토콜 구현: " + message);
		
		//예시를 위해서 만든거임 바로 아래코드는 ㄴㄴ 지우삼 사실 아래 엄데이트텍스트에리아 에서 받아야함
		updateTextArea("마이콜 : " + message);

//		printwrite해가지고 그냥 보내면 되는거
		// 보내고 나면 지워야
		textField.setText("");
		textField.requestFocus();
	}
	
	//
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}

	private class ChatClientThread extends Thread {
		@Override
		public void run() {
			updateTextArea("...");
			// 예시임. 내부 클래스 쓰는 이
		}
		
	}
}
