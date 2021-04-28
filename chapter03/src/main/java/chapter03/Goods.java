package chapter03;

public class Goods {
//field
	static public int countOfGoods;

	public Goods() {
		Goods.countOfGoods++;
	}

	public String name; //모든 접근 가능(접근 제한 없음)
	protected int price; //같은 패키지 + *자식 접근 가
	int countStock; //디폴트, 같은 패키
	private int countSolc; //클래스 내부에서만 접근이 가

	
	void m() {
		countSolc = 100;
	}
}
