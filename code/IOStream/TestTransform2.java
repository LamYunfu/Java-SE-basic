package com.ouc.cs.Lucas.IO;
/*
 * 通过键盘输入
 * System.in 本身就是一个InputStream
 */
import java.io.*;
public class TestTransform2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStreamReader isr = 
				new InputStreamReader(System.in);
		//在转换流外面再包一层缓冲流，因为BufferedReader提供了ReadLine()功能，能够一次性读入一行
		BufferedReader br = new BufferedReader(isr);
		String s = null;
		try{
			s = br.readLine();
			while(s != null){
				if(s.equalsIgnoreCase("exit"))		//exit表示输入的结束符
					break;
				System.out.println(s.toUpperCase());  //把输入字母转换成大写
				s = br.readLine();
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
