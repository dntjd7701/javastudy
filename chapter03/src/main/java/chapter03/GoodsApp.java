
package chapter03;

public class GoodsApp {

	public static void main(String[] args) {

		Goods goods = new Goods();
		
		//public은 접근 제한이 없다.
		goods.name = "cameara";
		
		//protected는 같은 패키지 안에서 접근이 가능하다. **자식에서만 접근이 가능하다.
		//goods.price = 1000;
		
		
		
		// 디폴트는 같은 패키지에서만 접근이 가능하다.
		//goods.countStock = 50;

		// private은 같은 클래스 내에서만 접근이 가능하다.
		// goods.countSolc = 20;



		Goods goods2 = new Goods();
		Goods goods3 = new Goods();

		System.out.println(Goods.countOfGoods);

	}

}
