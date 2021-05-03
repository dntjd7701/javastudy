package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {

	public static void main(String[] args) {

		InputStream is = null;
		OutputStream os = null;

		// memory든 file이든 읽고 쓰는 작업이 동일하다는걸 알아야
		try {
			is = new FileInputStream("KakaoTalk_Photo_2021-04-29-12-31-30.jpeg");
			os = new FileOutputStream("KakaoTalk_Photo_2021-04-29-12-31-30.copy.jpeg");

			int data = -1;
			while ((data = is.read()) != -1) {
				os.write(data);
			}
		} catch (FileNotFoundException e) {
			System.out.println("file not found : " + e);
		} catch (IOException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (is != null) {
					os.close();
				}
			} catch (IOException e) {
				System.out.println("plz check again");
			}
		}
	}

}
