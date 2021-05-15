package final01;

import java.util.Scanner;

public class Gugudan {
	
	static int resultNumber = 0;
	
	public static void main( String[] args ) {
		int l = randomize( 1, 9 );
		int r = randomize( 1, 9 );
		// 정답 
		resultNumber = l * r;

		//함수의 return값을 answerNumbers에 넣었음으로
		// answerNumbers에는 9개의 겹치지 않는 숫자 값이 들어있다.
		int[] answerNumbers = randomizeAnswers();
		
		// randomize 의 결과로 0~7까지의
		// 0,8로 되어있던데 어떤 의도였는지 물어보기!!!
		int loc = randomize( 0, 9 );
		
		answerNumbers[ loc ] = resultNumber;
		
		System.out.println( l + " x " + r + " = ?" );
		
		int length = answerNumbers.length;
		for( int i = 0; i < length; i++ ) {
			if( i % 3 == 0 ) {
				System.out.print( "\n" );
			} else {
				System.out.print( "\t" );
			}
			
			System.out.print( answerNumbers[ i ] );
		}

		System.out.print( "\n\n" );
		System.out.print( "answer:" );

		Scanner s = new Scanner( System.in );
		// user 정답 
		int userAnswer = s.nextInt();
		s.close();
		
		System.out.println(userAnswer == resultNumber ? "정답" : "오답");
		
		
		
		

	}

	private static int randomize( int lNum, int rNum ) {
		// 0~7 까지의 값이 배정되어있다. 총  8개  
        int random = (int) ( Math.random() * rNum ) + lNum;
        return random;
	}
	
	// 이 함수의 결과,  boardNumbers 배열 안에는
	// 서로 겹치지 않는 9개의 1~81까지의 범위의 랜덤한 숫자가 참조되어있다.
	private static int[] randomizeAnswers() {

		final int COUNT_ANSWER_NUMBER = 9;
		final int MAX_ANSWER_NUMBER = 81;
		
		// borad, 화면에 나와야할 9개의 숫자들 배열에 담
		int[] boardNumbers = new int[ COUNT_ANSWER_NUMBER ];
		int occupied = 0;
		
		// 9번 
		while( occupied < COUNT_ANSWER_NUMBER ) {
			
			// 1 ~ 81까지의 숫자 중 랜덤한 숫자가 random 변수에 담기게 된다.
			// Math.random() >> 0.0 < 1.0 
	        int random = ( int )( Math.random() * MAX_ANSWER_NUMBER ) + 1;
	        
	        // false로 초기화 
	        boolean evaluted = false;
	        // 1회차 : occupied 는 0으로 초기화 했음으로 실행하지 않는다.
	        // 2회차 : 1회, 실행하면서, 배열 boardNumber안의 값이 결과값과 이미 저장된 랜덤값에 
	        // 겹치지 않도록, 같은 경우 반복문을 종료시키고,  boardNumbers배열에 넣지 않도록 
	        // 겹칠 경우 evaluted를 true로 변경하여 아래 if문이 실행되지 않도록 한다.
	        for( int i = 0; i < occupied; i++ ) {
	        	if(random == boardNumbers[i] || random == resultNumber) {
	        		evaluted = true;
	        		break;
	        	}
	        }
	        // 1회차,
	        // 초기값이 false 이므로 true가 되어 실행된다.
	        // boradNumber[0]에 랜덤값이 들어가게되고,  occupied 를 1 증가 시킨다.
	        if( !evaluted ) {
	        	boardNumbers[ occupied++ ] = random;
	        }
		}
		
        return boardNumbers;
	}	
}