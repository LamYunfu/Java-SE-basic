package com.ouc.cs.Lucas.IO;
import java.io.*;
public class TestDataStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//���ڴ��з���һ���ֽ����飨byte array������СĬ��,��Ϊ���������Ŀ�ĵ�
		ByteArrayOutputStream baos = 
				new ByteArrayOutputStream();
		DataOutputStream dos = 
				new DataOutputStream(baos);
		try{
			dos.writeDouble(Math.random());				//д��һ�������
			dos.writeBoolean(true);				//bool�������ڴ���ռһ����
			ByteArrayInputStream bais =
					new ByteArrayInputStream(baos.toByteArray());	
			//�Ե�ǰ��ByteArrayOutputStreamΪ����������һ���µ�byte array.���Ұ�ԭ��������copy��ȥ
			System.out.println(bais.available());
			//Returns the number of remaining bytes that can be read
			DataInputStream dis = new DataInputStream(bais);
			System.out.println(dis.readDouble());			//����ʽ��������д�ȶ�
			System.out.println(dis.readBoolean());
			dos.close();
			dis.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
