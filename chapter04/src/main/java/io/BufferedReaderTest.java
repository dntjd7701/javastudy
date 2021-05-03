package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class BufferedReaderTest {

	public static void main(String[] args) {
		// close 하기 위해서 밖으로 나와야 함.
		// 얘를 닫으면 다 닫는거와 같으니깐 
		// 얘만 딱 
		BufferedReader br = null;
		
		try {
			// 기반 스크림
			// 현재 프로젝트의 소스를 읽어들임.
			FileReader fr = new FileReader("./src/main/java/io/BufferedReaderTest.java");
			
			// 보조 스크림 
			br = new BufferedReader(fr);
			
			String line = null;
			
			int index = 0;
			
			while((line = br.readLine()) != null) {
				index++;
				System.out.print(index);
				System.out.print(":");
				System.out.print(line);
				System.out.println("");
			}
		} catch (FileNotFoundException e) {
			System.out.println("file not found : " + e);
		} catch (IOException e) {
			System.out.println("error : " + e);
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
