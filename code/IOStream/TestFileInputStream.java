package com.ouc.cs.Lucas.IO;
import java.io.*;
public class TestFileInputStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int b = 0;
		FileInputStream in = null;
		try{
			in = new FileInputStream("C:\\Users\\���Ƹ�\\workspace\\��ʿ��J2SE�̳�\\src\\com\\ouc\\cs\\Lucas\\IO\\TestFileInputStream.java");
		}catch(FileNotFoundException e){
			System.out.println("�Ҳ���ָ���ļ�");
			System.exit(-1);
		}
		
		try{
			long num = 0;
			while((b = in.read()) != -1){		//-1��ʾ�����ļ��Ľ���λ��
				System.out.print((char)b);
				num++;
			}
			in.close();
			System.out.println();
			System.out.println("һ����ȡ��" + num +"���ֽ�");
		}catch(IOException e1){
			System.out.println("�ļ���д����");
			System.exit(-1);
		}
	}

}
