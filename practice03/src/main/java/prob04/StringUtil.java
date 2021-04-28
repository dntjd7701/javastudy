package prob04;

public class StringUtil {
	
	
	// null값이 붙어서 초기화 시킴.
	private static String result = "";
	
	public static String concatenate(String[] str) {
		
		for(int i = 0; i < str.length; i++) {
				result += str[i];
		}
		
		return result;
		
	}
}
