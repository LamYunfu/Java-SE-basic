package com.ouc.cs.Lucas.IO;

import java.io.*;
public class TestObjectIO {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		T t = new T();
		t.k = 0;
		FileOutputStream fos = new FileOutputStream("C:\\Users\\蓝云甫\\Desktop\\test.java");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(t);
		oos.flush();
		oos.close();
		
		FileInputStream fis = new FileInputStream("C:\\Users\\蓝云甫\\Desktop\\test.java");
		ObjectInputStream ois = new ObjectInputStream(fis);
		T tReaded = (T)ois.readObject();
		System.out.println(tReaded);
	}

}

class T implements Serializable{			//实现序列化接口，标记性接口，没有定义方法，给编译器看，通知这个类可以序列化
	int i = 0;
	int j = 15;
	double d = 2.3;
	int k = 15;
}