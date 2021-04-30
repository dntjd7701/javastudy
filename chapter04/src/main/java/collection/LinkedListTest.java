package collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class LinkedListTest {

	public static void main(String[] args) {
		List<String> l = new LinkedList<>();

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
