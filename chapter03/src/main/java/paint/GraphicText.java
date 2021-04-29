package paint;

public class GraphicText implements Drawable {

	private String text;

	public GraphicText(String text) {

		this.text = text;

	}

	@Override
	public void draw() {
		System.out.println("Text "+ text + "를 그렸습니다.");
		// TODO Auto-generated method stub

	}

}
