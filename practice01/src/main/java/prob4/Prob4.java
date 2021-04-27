package prob4;

import java.util.Scanner;

public class Prob4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("문자열을 입력하세요 : ");
		String text = scanner.nextLine();

		String[] array_word;
		array_word = text.split("");

		for (int j = 0; j < array_word.length; j++) {

			for (int i = 0; i <=j ; i++) {
				System.out.print(array_word[i]);

			}

			System.out.println("");
		}

		scanner.close();
	}

}
