package chapter03;

public class MyClass {
//	static을 참조하기 위해선 static으로 !
	
	private static MyClass instance;
	
	private MyClass() {
		
	}
	
//	Singleton + Factory Method
//	프로그램이 실행되는 동안에 객체가 딱 하나 있는거 = Singleton
	
	public static MyClass getInstance() {
		if(instance == null) {
			instance = new MyClass();
		}
		return instance;
	}
}
