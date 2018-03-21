package com.ouc.cs.Lucas.IO;
import java.io.*;
public class TestPrintStream1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintStream ps = null;
		try{
			FileOutputStream fos =
					new FileOutputStream("C:\\Users\\蓝云甫\\Desktop\\test.java");
			ps = new PrintStream(fos);
		}catch(IOException e){
			e.printStackTrace();
		}
		if(ps!= null){
			//默认的system.out的输出位置是控制台，可以通过setOut方法改变输出位置
			System.setOut(ps);			//Reassigns the "standard" output stream to the PrintStream object
		}
		int ln = 0;
		for(char c = 0; c < 60000;c++){
			System.out.print(c + " ");
			if(ln++ >= 100){
				System.out.println();
				ln = 0;
			}
		}
	}

}
