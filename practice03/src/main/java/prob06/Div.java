package prob06;

public class Div extends Arithmetic{
	private int a;
	private int b;
	
	@Override
	public void setValue(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public int calculate() {
		return a / b;
	}
}
