package com.ouc.cs.Lucas.IO;
import java.io.*;
public class TestFileOutputStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int b = 0;
		FileInputStream in = null;
		FileOutputStream out = null;
		try{
			in = new FileInputStream("C:\\Users\\蓝云甫\\workspace\\马士兵J2SE教程\\src\\com\\ouc\\cs\\Lucas\\IO\\TestFileInputStream.java");
			out = new FileOutputStream("‪C:\\Users\\蓝云甫\\Desktop\\test.java");
			while((b = in.read()) != -1){
				out.write(b);
			}
			in.close();
			out.close();
		}catch(FileNotFoundException e){
			System.out.println("打开文件出错。");
			System.exit(-1);
		}catch(IOException e){
			System.out.println("文件读写过程中出现错误");
			System.exit(-1);
		}
		System.out.println("文件已复制");
	}

}
