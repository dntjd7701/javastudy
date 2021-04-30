package prob5;

public class MyStack {
	private String[] data;
	private int top = -1;

	public MyStack(int size) {
		data = new String[size];
	}

	public void push(String data) {
		if (data.length() == top) {
			resize();
		}
		top++;

	}

	private void resize() {
		String[] temp = new String[data.length * 2];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = data[i];
		}
		String[] data = new String[temp.length];
		data = temp;

	}

	public void isEmpty() {
		if (top < 0) {
			
		}
	}

	public String pop() throws MyStackException {

	}
}