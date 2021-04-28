package chapter03;

public class Goods2App {

	public static void main(String[] args) {

		Goods2 goods = new Goods2();
		goods.setName("Nicon");
		goods.setPrice(10000);
		goods.setCountSold(20);
		goods.setCountStock(30);

		System.out.println(goods.calcDiscountPrice(0.5));
		goods.setPrice(-1);

		goods.showInfo();

		
		//자, 생성자에 초기값을 설정해주니 이리 간단해짐  
		Goods2 goods2 = new Goods2("Canon", 20000, 20, 30);
		goods2.showInfo();
	}

}
