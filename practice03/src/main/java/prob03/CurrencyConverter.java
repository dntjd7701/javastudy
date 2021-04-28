package prob03;

public class CurrencyConverter {
	
	private static double rate;
	
	
	public static void setRate(double r) {
		rate = r;
	}
	
	public static double toDollar(double won) {
		won = won/rate;
		return won;
		
	}
	
	public static double toKRW(double dollar) {
		dollar = dollar * rate;
		return dollar;
	}

}
