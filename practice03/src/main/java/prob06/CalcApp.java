package prob06;

import java.util.Scanner;

public class CalcApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while( true ) {
			/*  코드를 완성 합니다 */
			System.out.print( ">> " );
			String expression = scanner.nextLine();
			
			
			// quit 입력 시 종
			if( "quit".equals( expression ) ) {
				break;
			}
			
			// " " 공백으로 구분하여 배열에 넣
			String[] tokens = expression.split( " " );
			
			// 연산자를 포함하여 총 3개를 넘으면 오류로, 다시 처음부터 재입력 명령 
			if( tokens.length != 3 ) {
				System.out.println( ">> 알 수 없는 식입니다.");
				continue;
			}
			
			// String 으로 받은 입력을 int 로 변환 
			int lValue = Integer.parseInt( tokens[ 0 ] );
			int rValue = Integer.parseInt( tokens[ 2 ] );
			
			// 연산자에 따라서 switch사용 
			switch( tokens[ 1 ] ) {
				case "+" : {
					Arithmetic add = new Add();
					add.setValue( lValue, rValue );
					int result = add.calculate();
					System.out.println( ">> " + result );
					
					break;
				}
				case "-" : {
					Arithmetic sub = new Sub();
					sub.setValue( lValue, rValue );
					int result = sub.calculate();
					System.out.println( ">> " + result );
					
					break;
				}
				case "*" : {
					Arithmetic mul = new Mul();
					mul.setValue( lValue, rValue );
					int result = mul.calculate();
					System.out.println( ">> " + result );
					
					break;					
				}
				case "/" : {
					Arithmetic div = new Div();
					div.setValue( lValue, rValue );
					int result = div.calculate();
					System.out.println( ">> " + result );
					
					break;
				}
				default :  {
					System.out.println( ">> 알 수 없는 연산입니다.");
				}
			}
		}
		
		scanner.close();

	}

}
