package prob04;



public class Prob04 {

	public static void main(String[] args) {
		char[] c1 = reverse( "Hello World" );
		printCharArray( c1 );
		
		char[] c2 = reverse( "Java Programming!" );
		printCharArray( c2 );
	}
	
	public static char[] reverse(String str) {
		char[] a1 = str.toCharArray();
		char[] reverse1 = new char[a1.length];
		for(int i = 0; i<a1.length; i++) {
			reverse1[a1.length-1-i]=a1[i];
		}
		
		/* 코드를 완성합니다 */
		return reverse1;
	}

	public static void printCharArray(char[] array){
		/* 코드를 완성합니다 */
		System.out.println( array );
	}
}