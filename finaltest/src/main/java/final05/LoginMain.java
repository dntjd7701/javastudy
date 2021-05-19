package final05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginMain {

	public static void main(String[] args) {
		
		// 1. 사용자 입력 받기 
		Scanner sc = new Scanner(System.in);
		
		// User형의 리스트 생성 
		List<User> joinUsers = new ArrayList<User>();
		// User형의 객체 참조값 추가 
		joinUsers.add(new User("John", "1234"));

		System.out.println("아이디를 입력하시오 : ");
		String id = sc.nextLine();

		System.out.println("비밀번호를 입력하시오 : ");
		String password = sc.nextLine();

		try {
			login(joinUsers, new User(id, password));
			System.out.println("로그인 성공");
		} catch (UserNotFoundException ex) {
			System.out.println("사용자를 찾을 수 없습니다. ");
			return;
		} catch (PasswordDismatchException ex) {
			System.out.println("비밀번호가 틀렸습니다.");
			return;
		} finally {
			sc.close();
		}
	}
	
	
	public static void login(List<User> users, User user) throws UserNotFoundException, PasswordDismatchException {
		if(!users.contains(user)) {
			throw new UserNotFoundException();
		}
		
		User saveUser = users.get(users.indexOf(user));
		
		if(!saveUser.getPassword().equals(user.getPassword())){
			throw new PasswordDismatchException();
		}
	}
}
