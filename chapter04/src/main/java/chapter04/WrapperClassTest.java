package chapter04;

public class WrapperClassTest {

	public static void main(String[] args) {

		Integer i = new Integer(10);
		Character c = new Character('c');
		Boolean b = new Boolean(true);
		
		
//		Auto Boxing
//		메모리 낭비를 줄일 수 있음. 같은 값을 가진 객체를 생성하지 않아서 ㅇㅇ
		
		//그니까 int 는 기본 자료형이고 Integer는 객체를 생성한거야
//		근데 new로 하지 않았기 때문에 같은 reference를 참조해서 literal pool을 이용할 수 있어
//		근데 또 객체이기 때문에 null값을 넣을 수도 있고
//		Auto Boxing 과 Auto Unboxing으로 사용하기도 편해 
		
		Integer j1 = 10;
		Integer j2 = 10;		
		System.out.println(j1==j2);
		System.out.println(i==j2);
		
//		Auto Unboxing
//		int j3 = j1.intValue();
		int j3 = j1;
	}

}
