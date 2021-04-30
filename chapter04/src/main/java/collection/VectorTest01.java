package collection;

import java.util.Enumeration;
import java.util.Vector;

public class VectorTest01 {

	public static void main(String[] args) {
//		옛날 백터 쓰는 방식, ㄴㄴ함
//		List<String> v = new Vector<>();
//		요즘은 이렇게 써서 일관성을 유지해줘야함.

		Vector<String> v = new Vector<>();

		v.addElement("둘리");
		v.addElement("마이콜");
		v.addElement("또치");

//		1. 직접 가서 데이터를 받아온다.
//		2. 데이터를 던지기만 하면 알아서 반환(관심있는 애들이)

//		순회 1.
		int count = v.size();
		for (int i = 0; i < count; i++) {
			String s = v.elementAt(i);
			System.out.println(s);
		}
//		삭제
		v.removeElementAt(2);
		
//		순회 2. 순회하는 객체를 사용 Iterable 은 아님 
		Enumeration<String> e = v.elements();
		
		while(e.hasMoreElements()) {
			String s = e.nextElement();
			System.out.println(s);
		}
		
	}

}
