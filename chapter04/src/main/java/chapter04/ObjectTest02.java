package chapter04;

public class ObjectTest02 {

	public static void main(String[] args) {

		Point p1 = new Point(10, 20);
		Point p2 = new Point(10, 20);
		Point p3 = p2; // p2, p3는 같은 객
		
//		== : 두 객체의 동일성 (referece 값)
		System.out.println(p1 == p2);
		System.out.println(p2 == p3);
		
//		.equal() : 두 객체의 동질성 비교(안의 내용)
//		Object의 기본 구현은 ==와 같다.
//		내용 비교를 하고 싶으면 override 해줘야 함.
		
		System.out.println(p1.equals(p2));
		System.out.println(p2.equals(p3));
	}

}
