package paint;


//추상 메소드가 있으면, 추상 클래스 ! 객체를 생성하면 안돼기 때문에 class abstract 기입

public abstract class Shape {
	private String lineColor;
	private String fillColor;
	
	public abstract void draw();
}
