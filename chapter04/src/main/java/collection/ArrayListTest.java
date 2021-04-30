package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ArrayListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		interface를 쓰는 이유
//		ArrayList 쓰다가 어? 멀티 쓰레드 사용할 일이 생긴다~
//		그럼 그냥 객체만 Vector로 바꿔주면 코드 수정 안해도됌
//		하지만 요즘 추세는 그것마저도 코드를 직접 수정하지 않도록 
//		하는게 추세 
		
		List<String> l = new ArrayList<>();

		l.add("마이콜");
		l.add("둘리");
		l.add("또치");

//		순회 1. 훨 간단하고 좋아여

		for (int i = 0; i < l.size(); i++) {
			String s = l.get(i);
			System.out.println(s);
		}

		l.remove(2);

//		순회 2. Iterable 사용 !!!!

		Iterator<String> it = l.iterator();

		while (it.hasNext()) {
			String s = it.next();
			System.out.println(s);
		}

//		순회 3. 순회 2의 개량 버전 !!
//		for~each 확장된 for문이라고도 함. 매우 좋아여
//		******
		for(String s:l) {
			System.out.println(s);
		}

	
	}

}
