package final06;

public class Reservation {
	private int movie;
	private String name;
	
	public Reservation(int movie, String name) {
		this.movie = movie;
		this.name = name;
	}
	public int getMovie() {
		return movie;
	}
	public String getName() {
		return name;
	}
}
