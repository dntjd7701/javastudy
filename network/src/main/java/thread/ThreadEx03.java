package thread;

public class ThreadEx03 {

	public static void main(String[] args) {

		Thread thread1 = new DigitThread();
		Thread thread2 = new AlphabetThread();
		
// runnable 구현한것만 

		Thread thread3 = new Thread(new UppercaseAlphabetRunnablelmpl());

		thread2.start();
		thread1.start();
		thread3.start();
	}

}
