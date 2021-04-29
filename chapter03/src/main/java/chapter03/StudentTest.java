package chapter03;

public class StudentTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s1 = new Student();
		s1.setName("둘");
		s1.setGrade(4);
		s1.setMagjor("CS");
		
		Person p1 = s1; // upcasting(암시적)		
		Student s2 = (Student)p1; //downcasting(명시적)

	}

}
