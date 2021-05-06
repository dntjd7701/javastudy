package codingtestfor;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Scanner;

public class codingtest2739 {

//	public static void main(String[] args) throws Exception, IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//		int a = Integer.parseInt(br.readLine());
//
//		br.close();
//
//		if (a >= 1 && a <= 9) {
//			for (int i = 1; i < 10; i++) {
//				System.out.println(a + " * " + i + "=" + (a * i));
//			}
//		}
//
//	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		sc.close();
		
		if(N >= 1 && N <= 9 ) {
			for(int i = 1; i < 10; i++) {
				System.out.println(N + " * " + i + " = " + (N*i));
			}
		}
		
	}

}
