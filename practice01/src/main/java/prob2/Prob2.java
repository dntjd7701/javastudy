package prob2;

public class Prob2 {
	public static void main(String[] args) {
		/* 코드 작성 */

		int arr[][] = new int[9][10];

		for (int i = 0; i < arr.length; i++) {
			int num = i + 1;
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = num;
				num++;

				System.out.print(arr[i][j] + " ");
			}
			System.out.println("");
		}

	}

}