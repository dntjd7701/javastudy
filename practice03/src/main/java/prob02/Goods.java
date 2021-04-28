package prob02;

public class Goods {
	private String juice;
	private int price;
	private int count;
	
	
	public Goods() {
	}
	public Goods(String juice, int price, int count) {
		this.juice = juice;
		this.price = price;
		this.count = count;
	}
	
		
public String getJuice() {
		return juice;
	}


	public void setJuice(String juice) {
		this.juice = juice;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public void printGoods(String juice, int price, int count) {
		System.out.println(juice + "(가격:" + price + "원) " + count + "개 입고 되었습니다.");
	}

}
