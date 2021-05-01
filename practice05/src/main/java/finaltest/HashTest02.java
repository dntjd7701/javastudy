package finaltest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class HashTest02 {

	public static void main(String[] args) {

		Set<Gugudan> s = new HashSet<>();
//		randome 값 받아서 저장 
		Random leftValue = new Random();
		Random rightValue = new Random();
		
//		1~9까지의 값 중 값을 받아서 answer에 미리 저장 
		int left = leftValue.nextInt(9)+1;
		int right = leftValue.nextInt(9)+1;
		int answer = left*right;
		
		
		System.out.println(left + "x" + right);
			
		
		for (int i = 0; i < 8; i++) {
			s.add(new Gugudan((leftValue.nextInt(9)+1), (rightValue.nextInt(9)+1)));
//			System.out.println(s.toString());
		}

		s.add(answer);
		
		Iterator<Gugudan> it = s.iterator();
		
		// 이 값들을 이중 배열 안에 넣고 , answer도 랜덤한 곳에 끼어 넣게 한 다음 출력?ㄲ
//		
//		do {
//		} 
		while(!it.hasNext()) {
			System.out.println(s);
		}
		
		System.out.println(" " + answer);			
		
		
		
		
		
//		같은 값이라 생각하고 들어가지 않아야
//		
//		for(Gugudan g : s) {
//			System.out.println(g);
//		}
//		
	}

}
