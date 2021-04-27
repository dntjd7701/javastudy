package prob05;

import java.util.Random;
import java.util.Scanner;

public class Prob05 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num1 = 1;
		int num2 = 100;
		int a = 0;
		while (true) {

			/* 게임 작성 */

			// 정답 램덤하게 만들기
			Random random = new Random();
			int correctNumber = random.nextInt(100) + 1;
			System.out.println(correctNumber);

			System.out.println("수를 결정하였습니다. 맞추어 보세요");

			while (true) {

				System.out.println(num1 + "-" + num2);

				int num = scanner.nextInt();

				System.out.println(++a + ">>" + num);
				if (correctNumber == num) {
					break;

				} else if (correctNumber > num) {
					num1 = num;

					System.out.println("더 높게");

				} else {
					num2 = num;
					System.out.println("더 낮게");
				}

			}
			// 새 게임 여부 확인하기
			System.out.print("다시 하겠습니까(y/n)>>");
			String answer = scanner.next();
			if ("y".equals(answer) == false) {
				break;
			} else {

			}
		}

		scanner.close();
	}

}