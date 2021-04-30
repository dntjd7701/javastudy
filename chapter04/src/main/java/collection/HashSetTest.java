package collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		hash와 equls 
//		hash 비교해서 같으면 equls로 다시 한번 값 비교 ㅇㅇ

		Set<String> s = new HashSet<String>();
		
//		이 의미는 내용으로 찾는다는 것을 의미함.
		String s1 = new String("도우넛");
		String s2 = new String("도우넛");

		s.add("둘리");
		s.add("마이콜");
		s.add("또치");
		s.add(s1);

		int count = s.size();
//		Set은 index가 없기 때문에 순회를 해야해 
		System.out.println(count);
		System.out.println(s.contains(s2));
		
//		순회 1.
		Iterator<String> it = s.iterator();
		while(it.hasNext()) {
			String str = it.next();
			System.out.println(str);
		}
	}

}
