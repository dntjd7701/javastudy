package collection;

import java.util.HashSet;
import java.util.Set;

public class HashTest02 {

	public static void main(String[] args) {
//		random하게 돌렸다 치고 
		Set<Gugudan> s = new HashSet<>();
		
		s.add(new Gugudan(2, 3));
		s.add(new Gugudan(9, 9));
		s.add(new Gugudan(3, 2));
//		같은 값이라 생각하고 들어가지 않아야
		s.add(new Gugudan(3, 2));
		
		
		for(Gugudan g : s) {
			System.out.println(g);
		}
	}

}
