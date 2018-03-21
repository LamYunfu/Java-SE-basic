package com.ouc.cs.Lucas.IO;

import java.io.*;
public class TestTransform1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			//OutputStreamWriter可以将FileOutputStream转换成字节流，可以直接往里传字符串
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream("C:\\Users\\蓝云甫\\Desktop\\test.java"));
			osw.write("thisiswhatyoucamefor");
			System.out.println(osw.getEncoding());
			osw.close();
			osw = new OutputStreamWriter(
					//true表示在原来的文件上追加，否则会抹去原来的文件
					new FileOutputStream("C:\\Users\\蓝云甫\\Desktop\\test.java", true),	  
					"ISO8859_1");
			//ISO8859_1是欧洲统一编码
			System.out.println(osw.getEncoding());
			osw.write("thisiswhatyoucamefor");
			osw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
