package com.ouc.cs.Lucas.IO;

import java.io.*;
public class TestObjectIO {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		T t = new T();
		t.k = 0;
		FileOutputStream fos = new FileOutputStream("C:\\Users\\���Ƹ�\\Desktop\\test.java");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(t);
		oos.flush();
		oos.close();
		
		FileInputStream fis = new FileInputStream("C:\\Users\\���Ƹ�\\Desktop\\test.java");
		ObjectInputStream ois = new ObjectInputStream(fis);
		T tReaded = (T)ois.readObject();
		System.out.println(tReaded);
	}

}

class T implements Serializable{			//ʵ�����л��ӿڣ�����Խӿڣ�û�ж��巽����������������֪ͨ�����������л�
	int i = 0;
	int j = 15;
	double d = 2.3;
	int k = 15;
}