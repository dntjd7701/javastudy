package final06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static TicketingSystem ts = new TicketingSystem();

	public static void main(String[] args) {

		while (true) {
			try {
				System.out.println("\n== Ticketing System ==\n" + "1. Reserve\n" + "2. Cancel\n" + "3. Count\n"
						+ "4. exit");
				System.out.print("> ");
				
				String input = br.readLine();
				
				
				switch(Integer.parseInt(input)) {
				case 1:
					doReserve();
					break;
				case 2:
					doCancel();
					break;
				case 3:
					doCount();
					break;
				case 4:
					// 자원들을 정리하고 종료하는 정상적 종료 메소드임 
					System.exit(0);
					break;
				default:
					System.out.println("\n You just put wrong number. Try it again :)\n");
					
				}
			} catch (IOException e) {
				System.out.println("Please try it again with correct info.");
			} finally {

			}
		}
	}


	private static void doCount() throws IOException {
		printMovieList("Count");
		String id = br.readLine();
		System.out.print("Put your Id :");
		String name = br.readLine();
		if(Integer.parseInt(id) > 0 && Integer.parseInt(id) <= 3) {
			System.out.println("Reserved Count : " + ts.reservedCount(Integer.parseInt(id), name));
		} else {
			System.out.println("Incorrect input no.");
		}
	}

	private static void doCancel() throws IOException {
		System.out.println("Put your name : ");
		String name = br.readLine();
		ts.cancelReservation(name);
	}

	private static void doReserve() throws IOException{
		printMovieList("Reservation");
		String movie = br.readLine();
		if(Integer.parseInt(movie) > 0 && Integer.parseInt(movie) <= 3) {
			System.out.print("Put your name : ");
			// 이름 인풋 받기 
			String name = br.readLine();
			
			ts.addReservation(Integer.parseInt(movie), name);
		}
	}

	private static void printMovieList(String title) {
		System.out.println("\n==" + title + "==\n" + "1. The Lord of the Rings\n" + "2. IronMan\n" + "3. Amazing SpiderMan");
		System.out.print("> ");
	}

}
