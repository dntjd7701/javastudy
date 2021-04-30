package chapter04;

public class StringTest03 {

	public static void main(String[] args) {

		String s = "aBcABCabcAbC";

		System.out.println(s.length());
		System.out.println(s.charAt(2));
		System.out.println(s.indexOf("abc"));
//		탐색 위치 지정 
		System.out.println(s.indexOf("abc", 3));
//		탐색 위치가 넘어가서 못 찾음. -1 반환 
		System.out.println(s.indexOf("abc", 7));
		System.out.println(s.substring(3));
		System.out.println(s.substring(3, 5));

		String s2 = "   ab    cd    ";
		String s3 = "efg,hijk,lmn,opqr";

		String s4 = s2.concat(s3);
		System.out.println(s4);
		System.out.println("---" + s2.trim() + "----");
		System.out.println("-----" + s2.replaceAll(" ", "") + "------");

//		regular expression examples

		String p = "1000";
//		try {
//			int price = Integer.parseInt(p);
//			System.out.println(price);
//		} catch (NumberFormatException e) {
//			System.out.println("다시 입력하세요 ");
//		}
//		양수 표현하는 정규표현식 
		if(p.matches("\\d+")) {
			int price = Integer.parseInt(p);
			System.out.println(price);
			
		}else {
			System.out.println("noooooo");
		}

		
		String[] tokens1 = s3.split(",");
		for(String t : tokens1) {
			System.out.println(t);
		}
//		오 신세계 발견, 스플릿에 정규표현식 사용 가능한데 | 쓰면 한개씩 분리되네!
//		분리가 안된다면 그냥 하나 통으로 쏙 
		String[] tokens2 = s3.split(" ");
		for(String t : tokens2) {
			System.out.println(t);
		}

//		오 드디어 스트링 버퍼, 버퍼의 크기는 자유롭게 변함. 가변적으로
//		원랜 크기가 정해지잖아 ㅇㅇ 
//		원래 기본 사이즈가 잇음 그래도 가변적이여서 초반에 크게 잡아주는 경우도 많음
		
		StringBuffer sb = new StringBuffer();
		sb.append("Hello ");
		sb.append("World ");
		sb.append("JAVA ");
		sb.append(1.8);
		
		String k = sb.toString();
		
		System.out.println(k);
		System.out.println(sb);
		
//		String s6 = "Hello " + "World " + "JAVA " + 1.8;
//		이건 아래와 같음. 자동으로 바꿔주는거임
//		편의를 위해
		
		//sb2===sb3
		
		StringBuffer sb2 = new StringBuffer("Hello ");
		StringBuffer sb3 = sb2.append("World ");
		
//		우리가 기존에 쓰던 식은 요식으로 바꿔져서 실행되는거와 같음.
		String s6 = new StringBuffer("Hello ").append("World ").append("JAVA ").append(1.8).toString();
		
//		Warning!!! '+' 연산을 하지 말아야  할 때
//		String s7 = "";
//		for(int i = 0; i <10000; i++) {
////			s7 += i;
////			new를 계속하는거랑 같기 때문에 너~~~무 많이 잡아먹음 
//			s7 = new StringBuffer(s7).append(i).toString();	
//		}
		
		StringBuffer sb4 = new StringBuffer("");
		
		for(int i = 0; i< 100000; i++) {
			sb3.append(i);
		}
		String s7 = sb4.toString();
		//?
		System.out.println(s7.length());
		
//		드디어 wrapper class!!
	}

}
