package com.ouc.cs.Lucas.IO;
/*
 * ͨ����������
 * System.in �������һ��InputStream
 */
import java.io.*;
public class TestTransform2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStreamReader isr = 
				new InputStreamReader(System.in);
		//��ת���������ٰ�һ�㻺��������ΪBufferedReader�ṩ��ReadLine()���ܣ��ܹ�һ���Զ���һ��
		BufferedReader br = new BufferedReader(isr);
		String s = null;
		try{
			s = br.readLine();
			while(s != null){
				if(s.equalsIgnoreCase("exit"))		//exit��ʾ����Ľ�����
					break;
				System.out.println(s.toUpperCase());  //��������ĸת���ɴ�д
				s = br.readLine();
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
