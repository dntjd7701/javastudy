package chapter03;

public class Student extends Person {
	private int grade;
	private String magjor;
	
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getMagjor() {
		return magjor;
	}

	public void setMagjor(String magjor) {
		this.magjor = magjor;
	}

	public Student() {
//		자식의 모든 생성자에서 부모의 특정 생성자를 명시(explicity)하지 않으면
//		암시(implicity)적으로 부모의 기본생성자가 자동으로 호출된다.
// 		자식 생성자 앞에서 호출된다. 
//		super();
		System.out.println("Student() called");
	}
}
