package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class PhoneList01 {

	public static void main(String[] args) {

		BufferedReader br = null;
		try {
			// file 정보임
			File file = new File("phone.txt");

			if (!file.exists()) {
				System.out.println("file not found");
				return;
			}
			System.out.println("file info --------------");
			System.out.println(file.getAbsolutePath());
			System.out.println(file.length() + "bytes");
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(file.lastModified())));

			// 1. 기반 스트림

			FileInputStream fis = new FileInputStream(file);

			// 2. 보조 스트림(1) (byte -> char)
			InputStreamReader isr = new InputStreamReader(fis);

			// 3. 보조 스크림(2) (char -> String)

			br = new BufferedReader(isr);

			// 4. 처리

			String line;
			while ((line = br.readLine()) != null) {
//				System.out.println(line);
//				쪼개야 될 대상 넣기, 기준과 함께 
//				\t과 space 각각 기준이 적용된거
				
//				tap, \n, space
				StringTokenizer st = new StringTokenizer(line, "\t ");

				int index = 0;
				while (st.hasMoreTokens()) {
					String token = st.nextToken();
					if (index == 0) {// name
						System.out.print(token + ":");
					} else if (index == 1) {// num01
						System.out.print(token + "-");
					} else if (index == 2) {// num02
						System.out.print(token + "-");

					} else {// num03
						System.out.println(token);
					}
					index++;
				}
			}

		} catch (

		IOException e) {
			System.out.println("error : " + e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("file not found : " + e);
				}
			}

		}
	}
}
