package chat.gui;
import java.util.Scanner;

public class ChatClientApp {

	public static void main(String[] args) {
		String name = null;
		Scanner scanner = new Scanner(System.in);

		while( true ) {
			
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = scanner.nextLine();
			
			if (name.isEmpty() == false ) {
				break;
			}
			
			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}
		
		scanner.close();

		//1.  create socket
		//2.  connect server
		//3.  create iostream
		//4.  join
//		String line = br.readLine();
		String line = "JOIN:OK";
		if("JOIN:OK".equals(line)) {
			new ChatWindow(name).show();
		}
	}
	// 내부 클래스에서는 상위 클래스의 메소드를 사용할 수 있음 . 상황 봐서 잘 사용하삼
	

}
