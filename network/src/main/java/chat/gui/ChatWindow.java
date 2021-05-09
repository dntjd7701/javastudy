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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	
	private String nickname;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;
	
	public ChatWindow(String nickname, Socket socket, BufferedReader br, PrintWriter pw) {
		frame = new Frame(nickname);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		
		this.nickname = nickname;
		this.socket = socket;
		this.br = br;
		this.pw = pw;
	
	}

	public void show() {
		/**
		 *  1. UI 초기화
		 */
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent actionEvent ) {
				sendMessage();
			}
		});

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == e.VK_ENTER) {
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
		 *  2. IOStream 생성
		 */

		/**
		 *  3. Chat Client Thread 생성 (Receive Thread)
		 */
		updateTextArea(nickname +"님 환영합니다");
		new ChatClientThread().start();
		
	}
	
	private void sendMessage() {
		String message = textField.getText();
		pw.println(message);
		textField.setText("");
		textField.requestFocus();
	}
	
	private void updateTextArea(String msg) {
		textArea.append(msg);
		textArea.append("\n");
	}

	private void finish() {
		try {
			if(socket != null && !socket.isClosed())
				socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	
	private class ChatClientThread extends Thread{

		@Override
		public void run() {
			while(true) {
				try {
					String responseMsg = br.readLine();
					updateTextArea(responseMsg);
				} catch (SocketException e) {
					break;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}

