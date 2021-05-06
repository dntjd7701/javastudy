package codingtestfor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class codingtest8393 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int result = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int n = Integer.parseInt(br.readLine());
		
		if(1 <= n && n <= 10000) {
			for(int i = 1; i <= n; i++) {
				result += i;
				
			}
		}
		
		System.out.println(result);
		
		br.close();
		
	}

}
