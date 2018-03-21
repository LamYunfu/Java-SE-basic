package com.ouc.cs.Lucas.IO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestFileReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int b = 0;
		FileReader fr = null;
		try{
			fr = new FileReader("C:\\Users\\蓝云甫\\workspace\\马士兵J2SE教程\\src\\com\\ouc\\cs\\Lucas\\IO\\TestFileReader.java");
			while((b = fr.read()) != -1){
				System.out.print((char)b);
			}
			fr.close();
		}catch(FileNotFoundException e){
			System.out.println("打开文件出错");
			System.exit(-1);
		}catch(IOException e){
			System.out.println("读写出现错误");
		}
	}

}
