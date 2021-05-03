package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamTest {

	public static void main(String[] args) {

		BufferedOutputStream bos = null;
		try {
			
			// 기반 스트림 
			FileOutputStream fis = new FileOutputStream("test.txt");
			// 더 좋은 코드
			// BufferedOutputStream bos = new BufferedOutputStream(new
			// FileOutputStream("test.txt")));
			
			//보조 스트림 
			bos = new BufferedOutputStream(fis);
//			for(int i = 'a';i <'z'; i++)
			for (int i = 97; i <= 122; i++) {
				try {
					bos.write(i);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("cant't open " + e);
		} catch(IOException e) { 
			System.out.println("error");
		}
		finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
