package com.ouc.cs.Lucas.IO;
import java.io.*;
public class TestBufferedStream2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			BufferedWriter bw = new 
					BufferedWriter(new FileWriter("C:\\Users\\���Ƹ�\\Desktop\\test.java"));
			BufferedReader br = new 
					BufferedReader(new FileReader("C:\\Users\\���Ƹ�\\Desktop\\test.java"));
			String s = null;
			for(int i = 0;i < 100;i++){
				s = String.valueOf(Math.random());
				bw.write(s);
				bw.newLine();         //д��һ���س�,BufferedWriter�ṩ�Ĺ���
			}
			bw.flush();              //��ջ���������
			/*
			 * FileReader��Ķ���ÿ��ֻ�ܹ������ȡһ���ֽ�
			 * ���ǰ�װ��BufferedReader�����˻�����
			 * ����һ�δ��ļ��ж�ȡһ�е�����
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
