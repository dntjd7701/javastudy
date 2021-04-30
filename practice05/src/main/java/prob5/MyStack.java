package prob5;

public class MyStack {
	private String[] data;
	private int top = -1;

	public MyStack(int size) {
		top  = -1;
		data = new String[size];
	}

	public void push(String s) {
		if (data.length - 1 == top) {
			resize();
		}
		data[++top] = s;

	}
	public String pop() throws MyStackException {
		if(isEmpty()) {
			throw new MyStackException("EMPTYYYYYYY");
		}
		String result = data[top];
		data[top--] = null;
		return result;
		
	}
	

	private void resize() {
		String[] temp = new String[data.length * 2];
		for (int i = 0; i < top; i++) {
			temp[i] = data[i];
		}
		data = temp;

	}
//	왜 top==-1 이면 비어있는 상태인거지 ?
//	인덱스가 0 이전을 가르키고 있기 때문에 비어있다고 하는 것고 ㅏ같다.
	
	public boolean isEmpty() {
		return top == -1;
	}

}