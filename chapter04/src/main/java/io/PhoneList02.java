package io;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PhoneList02 {
	public static void main(String[] args) {
		Scanner scanner = null;
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

			System.out.println("number ---------------");

			scanner = new Scanner(file);

			// next 기본 나눔 문자 \t, \n, space
			while (scanner.hasNextLine()) {
				String name = scanner.next();
				String phone1 = scanner.next();
				String phone2 = scanner.next();
				String phone3 = scanner.next();
				
				System.out.println(name + ":" + phone1 + "-" + phone2 + "-" + phone3);
			}

		} catch (IOException e) {
			System.out.println("error : " + e);
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

	}
}
