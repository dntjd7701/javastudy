package chapter04;

public class StringTest02 {

	public static void main(String[] args) {

		
//		immutability
//		불변성 
//		String s = "hello";
//		System.out.println(s.toUpperCase());
//		
//		
//		String s1 = new String("hi");
//		String s2 = new String("hi");
//

//		
//		
//		Integer s1 = new Integer(1);
//		Integer s2 = new Integer(1);
//		
//		
//		if(s1==s2) {
//			System.out.println("oh");
//		}
//		
		
		String s1 = "abc";
		String s2 = "def";
		String s3 = s2;
		
		s2 = s1.toUpperCase();
		
		String s4 = s2.concat("??");
		String s5 = "!".concat(s2).concat("@");
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);
		
//		equls 주의 !
		
		System.out.println(equalsHello(null));
		
	}
	private static boolean equalsHello(String s) {
		return "Hello".equals(s);
	}
		

}
