package final06;

import java.util.ArrayList;
import java.util.List;

// List 에 추가 및 삭제 
public class TicketingSystem {
	List<Reservation> listReservation = new ArrayList<Reservation>();
	static int count = 0;
	public void addReservation(int movie, String name) {
		// Main 메소드로부터 받은 영화 no와 사용자 이름을
		// 생성한 Reservation 클래스의 객체 생성으로 값을 반환하여
		// 그 참조값을 List에 저장.
		Reservation r = new Reservation(movie, name);
		listReservation.add(r);
	}

	public boolean cancelReservation(String name) {
		Reservation del = null;
		for (Reservation r : listReservation) {
			if (r.getName() == name) {
				del = r;
				
			}
		}
		if (del != null) {
			return listReservation.remove(del);
		} else {
			System.out.println("Not Found");
			return false;
		}

	}

	public int reservedCount(int movie, String name) {
	
		for(Reservation r : listReservation) {
			if(r.getMovie() == movie) {
				count++;
			} else if(r.getName() != name) {
				count --;
				System.out.println("You canceled");
			} 
		}
		return count;
	}

}
