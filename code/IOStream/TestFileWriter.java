package com.ouc.cs.Lucas.IO;
import java.io.*;
public class TestFileWriter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int b = 0;
		FileReader fr = null;
		FileWriter fw = null;
		try{
			fr = new FileReader("C:\\Users\\蓝云甫\\workspace\\马士兵J2SE教程\\src\\com\\ouc\\cs\\Lucas\\IO\\TestFileReader.java");
			fw = new FileWriter("C:\\Users\\蓝云甫\\Desktop\\test.java");
			while((b = fr.read()) != -1){
				fw.write((char)b);
			}
			fr.close();
			fw.close();
		}catch(FileNotFoundException e){
			System.out.println("无法找到指定文件");
			System.exit(-1);
		}catch(IOException e){
			System.out.println("读写错误");
			System.exit(-1);
		}
		System.out.println("读写成功");
	}

}
