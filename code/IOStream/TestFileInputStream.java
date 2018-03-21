package com.ouc.cs.Lucas.IO;
import java.io.*;
public class TestFileInputStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int b = 0;
		FileInputStream in = null;
		try{
			in = new FileInputStream("C:\\Users\\蓝云甫\\workspace\\马士兵J2SE教程\\src\\com\\ouc\\cs\\Lucas\\IO\\TestFileInputStream.java");
		}catch(FileNotFoundException e){
			System.out.println("找不到指定文件");
			System.exit(-1);
		}
		
		try{
			long num = 0;
			while((b = in.read()) != -1){		//-1表示读到文件的结束位置
				System.out.print((char)b);
				num++;
			}
			in.close();
			System.out.println();
			System.out.println("一共读取了" + num +"个字节");
		}catch(IOException e1){
			System.out.println("文件读写错误");
			System.exit(-1);
		}
	}

}
