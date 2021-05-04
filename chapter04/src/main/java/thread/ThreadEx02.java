package thread;

public class ThreadEx02 {

	public static void main(String[] args) {

		//main 이 종료가 되더라도 쓰레드가 남아있으면 종료가 안된다는 실험 
		
		Thread thread1 = new DigitThread();
		Thread thread2 = new AlphabetThread();
		
		thread2.start();
		thread1.start();
		
		// java 는 쓰레드가 하나라도 남아있으면 완전히 종료되는것이 아니다.
		
	}

}
