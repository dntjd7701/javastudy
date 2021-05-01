package finaltest;

public class  Gugudan<T> {

	private int left;
	private int right;
	private int answer;
	
	public Gugudan(int left, int right) {
		this.left = left;
		this.right = right;
	}
	

	public Gugudan(int answer) {
		this.answer = answer;
	}


	@Override
	public String toString() {
		return " "+left*right;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (left*right);
//		result = prime * result + right;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gugudan other = (Gugudan) obj;
//		if (left != other.left)
//			return false;
//		if (right != other.right)
//			return false;
		if (left*right != other.left*other.right)
			return true;
		return true;
	}


	
}
