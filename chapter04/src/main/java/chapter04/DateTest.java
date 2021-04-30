package chapter04;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {

		Date now = new Date();

		System.out.println(now);

		printDate01(now);
		printDate02(now);
	}

	private static void printDate01(Date now) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(now);
		System.out.println(date);
	}

	private static void printDate02(Date now) {
//		year(+1900)
		int year = now.getYear() + 1900;
		System.out.println(year);
//		Month(0~11) + 1
		int month = now.getMonth() + 1;
//		day
		int date = now.getDate();
//		hour
		int hour = now.getHours();
//		min
		int minutes = now.getMinutes();
//		second
		int seconds = now.getSeconds();
		
		
		System.out.println( year + "-" + month + "-" + date + " " + hour + ":" + minutes + ":" + seconds);

	}

}