package com.ouc.cs.Lucas.IO;
import java.io.*;
public class TestBufferedStream {
	public static void main(String[] args){
		try{
			FileInputStream fis = new FileInputStream("C:\\Users\\蓝云甫\\workspace\\马士兵J2SE教程\\src\\com\\ouc\\cs\\Lucas\\IO\\TestFileInputStream.java");
			//相当于在FileInputStream这根管道上面再套接一层BufferedInputStream这根管道
			BufferedInputStream bis = new BufferedInputStream(fis);
			int c = 0;
			System.out.println(bis.read());
			System.out.println(bis.read());
			bis.mark(100);        //在第100个字节开始读
			for(int i = 0;i < 10 && (c = bis.read()) != -1;i++){
				System.out.print((char)c + " ");
			}
			System.out.println();
			bis.reset();     //读取位置回到100这个位置
			for(int i = 0;i < 10 && (c = bis.read()) != -1;i++){
				System.out.print((char)c + " ");
			}
			bis.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
