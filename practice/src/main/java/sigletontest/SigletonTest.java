package sigletontest;

public class SigletonTest {
class Singleton{
	private static Singleton one;
	private Singleton() {
		}

	public static Singleton getInstance() {
		if (one == null) {
			one = new Singleton();
		}
		return one;
	}
}
	public static void main(String[] args) {

		Singleton single1 = Singleton.getInstance();
		Singleton single2 = Singleton.getInstance();

		System.out.println("두 개체가 같은 객체인가 ? ==> ");
		System.out.println(single1 == single2);

	}

}
