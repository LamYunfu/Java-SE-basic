package com.ouc.cs.Lucas.IO;
import java.io.*;
import java.util.Date;
public class TestPrintStream3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = null;
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		try{
			FileWriter fw = new FileWriter("C:\\Users\\���Ƹ�\\Desktop\\test.java");
			PrintWriter log = new PrintWriter(fw);
			while((s = br.readLine()) != null){				//�ڿ���̨����
				if(s.equalsIgnoreCase("exit")) break;
				System.out.println(s.toUpperCase());			//ת���ɴ�д���ڿ���̨���
				log.println("----------");
				log.println(s.toUpperCase());					//������ļ�
				log.flush();
			}
			log.println("===" + new Date() + "===");
			log.flush();
			log.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
