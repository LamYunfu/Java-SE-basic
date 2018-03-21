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
			FileWriter fw = new FileWriter("C:\\Users\\蓝云甫\\Desktop\\test.java");
			PrintWriter log = new PrintWriter(fw);
			while((s = br.readLine()) != null){				//在控制台输入
				if(s.equalsIgnoreCase("exit")) break;
				System.out.println(s.toUpperCase());			//转化成大写后在控制台输出
				log.println("----------");
				log.println(s.toUpperCase());					//输出到文件
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
