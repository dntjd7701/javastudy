package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class KeyboardTest {

	public static void main(String[] args) {
		BufferedReader br = null;
//		???
//		Scanner hey = new Scanner(new InputStreamReader(System.in));

		try {
			// 1. 기반 스트림 (표준 입력, stdin, System.in, 만들 필요 없음)

			// 2. 보조 스트림(1) (byte|byte|byte -> char)
			InputStreamReader isr = new InputStreamReader(System.in, "utf-8");

			// 3. 보조 스트림(2) (char|char|char..|\n -> String == "char1char2char3..")
			br = new BufferedReader(isr);

			String line = null;

			while ((line = br.readLine()) != null) {
				if("quit".equals(line)) {
					break;
				}
				System.out.println(line);

			}

		} catch (UnsupportedEncodingException e) {
			System.out.println("error : " + e);
		} catch (IOException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				// 이 부분이 이해가 안돼 
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
