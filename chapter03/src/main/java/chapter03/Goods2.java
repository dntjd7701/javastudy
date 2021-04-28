package chapter03;

public class Goods2 {

	private String name;
	private int price;
	private int countSold;
	private int countStock;
	
	//생성자 만들어주기, 평소에는 자동으로 만들어주지만 초기값 생성자로 인해 새로 만들어줘야해 
	public Goods2() {
	}
	
	//다형성!!! 다양한 방식으로 호출하여 사용 
	public Goods2(String name, int price, int countSold, int countStock) {
		this.name = name;
		this.price = price;
		this.countSold = countSold;
		this.countStock = countStock;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		if(price < 0) {
			this.price = 0; 
		}
		this.price = price;
	}
	public int getCountSold() {
		return countSold;
	}
	public void setCountSold(int countSold) {
		this.countSold = countSold;
	}
	public int getCountStock() {
		return countStock;
	}
	public void setCountStock(int countStock) {
		this.countStock = countStock;
	}
	public void showInfo() {
		System.out.println(
				"name:"+name+", "+
				"price:"+price+", "+
				"countSold:"+countSold+", "+ 
				"countStock:"+countStock);
		
	}

	public int calcDiscountPrice(double rate) {
//		int discountPrice = price *  rate;
//		return discountPrice;
		return (int)(price * rate);
		
	}
	
}
