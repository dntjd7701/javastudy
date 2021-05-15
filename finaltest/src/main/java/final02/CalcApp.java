
package final02;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalcApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);		
		System.out.print("두 정수와 연산자를 입력하시오 >> ");
		
		//interface 변수 생성 및 초기
		Arithmetic ar = null;
		// 두 정수의 입력값과 연산자 받
		int left = scanner.nextInt();
		int right = scanner.nextInt();
		String s = scanner.next();
		
		//연산자 비
		switch(s) {
		case "+":
			
			//각 연산자에 맞는 클래스 호출/메소드 호
			ar = new Add();
			System.out.println(ar.calculate(left, right));
			break;
		case "-":
			ar= new Sub();
			System.out.println(ar.calculate(left, right));
			break;
		case "*":
			System.out.println(ar.calculate(left, right));
			break;
		case "/":
			System.out.println(ar.calculate(left, right));
			break;
		}
	}


}
