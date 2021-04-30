package prob01;

public class Printer {
	
//	public Printer() {
//		System.out.println();
//	}
//	public void println(int num) {
//		System.out.println(num);
//	}
//	public void println(boolean yesOrNo) {
//		System.out.println(yesOrNo);
//	}
//	public void println(double d) {
//		System.out.println(d);
//	}
//	public void println(String str) {
//		System.out.println(str);
//	}
	
	
	public <T> void println(T t) {
		System.out.println(t);
	}
	
//	타입이 다른 가변 변수 만들기
	public <T> void println(T... ts) {
		for(T t : ts) {
		System.out.print(t);
		System.out.print(" ");
		}
		System.out.println("");
	}
	
//	타입이 같은 가변 변수 만들기 
	public int sum(Integer... nums) {
		Integer sum = 0;
		for(Integer i : nums) {
			sum += i;
		}
		return sum;
		
	}

}
