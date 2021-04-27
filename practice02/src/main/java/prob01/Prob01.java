package prob01;

import java.util.Scanner;

public class Prob01 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int money = scanner.nextInt();
		System.out.println("금액:"+money);
		System.out.println("");
		int a=0;
		
		
		final int[] MONEYS = { 50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1 };
		
		
		for(int i=0;i<=MONEYS.length;i++) {
			a=money/MONEYS[i];
			System.out.println(MONEYS[i]+"원 : "+a+"개");
			money=money%MONEYS[i];
			
		}

		/* 코드 작성 */

		scanner.close();
	}
}