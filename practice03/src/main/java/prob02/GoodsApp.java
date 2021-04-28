package prob02;

import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];
		
		///yehahhhh
		

		// 상품 입력
		
		for(int i = 0; i < goods.length ; i ++) {
			String juice = scanner.next();
			int price = scanner.nextInt();
			int count = scanner.nextInt();
			
			
			goods[i] = new Goods(juice, price, count);
					
		}
		



		// 상품 출
		for(int i = 0; i < goods.length ; i ++) {
			
		goods[i].printGoods(goods[i].getJuice(), goods[i].getPrice(), goods[i].getCount());
		}
		
		
		// 자원정리
		scanner.close();
	}
}
