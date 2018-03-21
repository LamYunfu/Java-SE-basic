package com.ouc.cs.Lucas.IO;
import java.io.*;
public class TestPrintStream2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = args[0];
		if(fileName != null){
			list(fileName, System.out);
		}
	}
	
	public static void list(String f, PrintStream ps){
		try{
			BufferedReader br = 
					new BufferedReader(new FileReader(f));
			String s = null;
			while((s = br.readLine()) != null){
				ps.println(s);
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
