package com.ouc.cs.Lucas.IO;
import java.io.*;
public class TestBufferedStream {
	public static void main(String[] args){
		try{
			FileInputStream fis = new FileInputStream("C:\\Users\\���Ƹ�\\workspace\\��ʿ��J2SE�̳�\\src\\com\\ouc\\cs\\Lucas\\IO\\TestFileInputStream.java");
			//�൱����FileInputStream����ܵ��������׽�һ��BufferedInputStream����ܵ�
			BufferedInputStream bis = new BufferedInputStream(fis);
			int c = 0;
			System.out.println(bis.read());
			System.out.println(bis.read());
			bis.mark(100);        //�ڵ�100���ֽڿ�ʼ��
			for(int i = 0;i < 10 && (c = bis.read()) != -1;i++){
				System.out.print((char)c + " ");
			}
			System.out.println();
			bis.reset();     //��ȡλ�ûص�100���λ��
			for(int i = 0;i < 10 && (c = bis.read()) != -1;i++){
				System.out.print((char)c + " ");
			}
			bis.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
