package chapter04;

import java.util.Calendar;

public class CalendarTest {

	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();
		printDate(cal);
		
		
		cal.set(Calendar.YEAR, 1995);
		cal.set(Calendar.MONTH, 3); // month -1
		cal.set(Calendar.DATE, 20);
		printDate(cal);
		
		cal.set(2013, 7, 14);
		cal.add(Calendar.DATE, 100);
		printDate(cal);
	
	}
	
	private static void printDate(Calendar cal) {
		final String[] DAYS = {"일 ", "월 ", "화 ", "수 ","목 ","금 ","토 "};
		
//		year
		int year = cal.get(Calendar.YEAR);
//		month(0~11) + 1
		int month = cal.get(Calendar.MONTH) + 1;
//		date
		int date = cal.get(Calendar.DATE);
//		day 1(일)~7(토), 0은 빠져있음 
		int day = cal.get(Calendar.DAY_OF_WEEK);
//		hour
		int hour = cal.get(Calendar.HOUR);
//		minute
		int minute = cal.get(Calendar.MINUTE);
//		second
		int second = cal.get(Calendar.SECOND);
		
		System.out.println( year + "년" + month + "월" + date + "일 " + DAYS[day-1] + "요일 " + hour + "시 " + minute + "분 " + second + "초 ");
		
	}

}
