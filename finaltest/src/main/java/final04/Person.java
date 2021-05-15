package final04;

public class Person {
	static int numberOfPerson = 0;
	private int age;
	private String name;
	
	
	public Person(String name) {
		this.age = 12;
		this.name = name;
	
		
	}
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
		
	}
	
	void selfIntrouduce() {
		System.out.println("내 이름은 " + name + "이며, 나이는 " + age + "살 입니다.");
	}
	
	static int getPopulation() {
		numberOfPerson++;
		return numberOfPerson;
	}
	
}
