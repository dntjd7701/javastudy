package codingtest;

import java.util.Scanner;

public class codingtest9498 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int score = scanner.nextInt();
		scanner.close();
		
		if(score >= 90) {
			System.out.println('A');
		} else if(80 <= score && score < 90) {
			System.out.println('B');
		} else if(70 <= score && score < 80) {
			System.out.println('C');
		} else if(60 <= score && score < 70) {
			System.out.println('D');
		} else {
			System.out.println('F');
		}
	}

}
