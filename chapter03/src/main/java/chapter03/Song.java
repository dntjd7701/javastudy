package chapter03;

public class Song {
	private String title;
	private String artist;
	private String album;
	private String composer;
	private int year;
	private int track;
	
	public Song() {
		
	}
	public Song(String title, String artist){
//		this.title = title;
//		this.artist = artist;
		
		//기존에 만들었던 생성자를 다시 호출해서 사용하고 싶을때, 즉, 코드의 중복을 막기 위해서
		//유지보수도 쉬움 
		//Good way!
		
		this(title, artist, null, null, 0, 0);
		
		//some codes....a lot
	}
	public Song(String title, String artist, String album, String composer, int year, int track) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.composer = composer;
		this.year = year;
		this.track = track;
		
		//some codes....... a lot 
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTrack() {
		return track;
	}

	public void setTrack(int track) {
		this.track = track;
	}

	public void show() {
		System.out.println(
				artist + " " + title + "(" + album + ", " + year + ", " + track + "번 track, " + composer + "작곡)");
	}

}
