package com.ouc.cs.Lucas.IO;
import java.io.*;
public class TestFileWriter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int b = 0;
		FileReader fr = null;
		FileWriter fw = null;
		try{
			fr = new FileReader("C:\\Users\\���Ƹ�\\workspace\\��ʿ��J2SE�̳�\\src\\com\\ouc\\cs\\Lucas\\IO\\TestFileReader.java");
			fw = new FileWriter("C:\\Users\\���Ƹ�\\Desktop\\test.java");
			while((b = fr.read()) != -1){
				fw.write((char)b);
			}
			fr.close();
			fw.close();
		}catch(FileNotFoundException e){
			System.out.println("�޷��ҵ�ָ���ļ�");
			System.exit(-1);
		}catch(IOException e){
			System.out.println("��д����");
			System.exit(-1);
		}
		System.out.println("��д�ɹ�");
	}

}
