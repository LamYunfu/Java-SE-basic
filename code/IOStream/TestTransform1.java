package com.ouc.cs.Lucas.IO;

import java.io.*;
public class TestTransform1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			//OutputStreamWriter���Խ�FileOutputStreamת�����ֽ���������ֱ�����ﴫ�ַ���
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream("C:\\Users\\���Ƹ�\\Desktop\\test.java"));
			osw.write("thisiswhatyoucamefor");
			System.out.println(osw.getEncoding());
			osw.close();
			osw = new OutputStreamWriter(
					//true��ʾ��ԭ�����ļ���׷�ӣ������Ĩȥԭ�����ļ�
					new FileOutputStream("C:\\Users\\���Ƹ�\\Desktop\\test.java", true),	  
					"ISO8859_1");
			//ISO8859_1��ŷ��ͳһ����
			System.out.println(osw.getEncoding());
			osw.write("thisiswhatyoucamefor");
			osw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
