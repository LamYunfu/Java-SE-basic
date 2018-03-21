package com.ouc.cs.Lucas.IO;
import java.io.*;
public class TestDataStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//在内存中分配一个字节数组（byte array），大小默认,作为数据输出的目的地
		ByteArrayOutputStream baos = 
				new ByteArrayOutputStream();
		DataOutputStream dos = 
				new DataOutputStream(baos);
		try{
			dos.writeDouble(Math.random());				//写入一个随机数
			dos.writeBoolean(true);				//bool类型在内存中占一个数
			ByteArrayInputStream bais =
					new ByteArrayInputStream(baos.toByteArray());	
			//以当前的ByteArrayOutputStream为蓝本，创建一个新的byte array.并且把原来的内容copy过去
			System.out.println(bais.available());
			//Returns the number of remaining bytes that can be read
			DataInputStream dis = new DataInputStream(bais);
			System.out.println(dis.readDouble());			//队列式读出，先写先读
			System.out.println(dis.readBoolean());
			dos.close();
			dis.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
