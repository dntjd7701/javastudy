package codingtest;

import java.util.Scanner;

public class codingtest2884 {
//if
	public static void main(String[] args) {

		 Scanner sc = new Scanner(System.in);
		 
		 int H = sc.nextInt();
		 int M = sc.nextInt();
		
		 if((0 <= H && H <= 23) && (0 <= M && M <= 59)){
			 if(M < 45) {
				 H--;
				 M = (M+60) - 45;
				 if(H < 0) {
					 H = 23;
				 }
				 System.out.println(H + " " + M);
			 } else if (M >= 45) {
				 M -= 45;
				 System.out.println(H + " " + M);
			 }
		 }
		
		 sc.close();
	}

}
