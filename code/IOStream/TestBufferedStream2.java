package com.ouc.cs.Lucas.IO;
import java.io.*;
public class TestBufferedStream2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			BufferedWriter bw = new 
					BufferedWriter(new FileWriter("C:\\Users\\蓝云甫\\Desktop\\test.java"));
			BufferedReader br = new 
					BufferedReader(new FileReader("C:\\Users\\蓝云甫\\Desktop\\test.java"));
			String s = null;
			for(int i = 0;i < 100;i++){
				s = String.valueOf(Math.random());
				bw.write(s);
				bw.newLine();         //写入一个回车,BufferedWriter提供的功能
			}
			bw.flush();              //清空缓冲区内容
			/*
			 * FileReader类的对象每次只能够往外读取一个字节
			 * 但是包装成BufferedReader，有了缓冲区
			 * 就能一次从文件中读取一行的内容
			 */
			while((s = br.readLine()) != null){
				System.out.println(s);
			}
			bw.close();
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
