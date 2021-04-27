package prob3;

import java.util.Scanner;

public class Prob3 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		/* 코드 작성 */
		int result = 0;
		int num = scanner.nextInt();

		System.out.println("숫자를 입력하세요: " + num);

		if (num % 2 == 0) {
			for (int i = 0; i <= num; i++) {
				if (i % 2 == 0) {
					result += i;
				}
			}

			System.out.println("결과 값 : " + result);
		} else {
			for (int i = 0; i <= num; i++) {
				if (i % 2 != 0) {
					result += i;
				}
			}
			System.out.println("결과 값 : " + result);
		}
		scanner.close();
	}
}
