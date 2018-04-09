package com.ouc.cs.Lucas.Thread;

import java.util.Date;

public class TestInterrupt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread thread = new MyThread();
		thread.start();
		try{
			Thread.sleep(10000);    //sleep��Thread��ľ�̬���������ĸ��߳��������Thread������������һ���߳�˯��
		}catch(InterruptedException e){
		}
		/*
		 * ����thread��interrupt()����,���߳�thread����һ���жϱ�־
		 * thread��sleep,wait�ȷ������������־λ��ͬʱ�׳�InterruptedException��������߳��еı�־λ
		 */
		thread.interrupt(); 
	}

}

class MyThread extends Thread{
	public void run(){
		while(true){
			System.out.println("===" + new Date() + "===");
			try{
				sleep(1000);				//ÿ��һ���ӣ���ӡһ��ϵͳʱ��
			}catch(InterruptedException e){
				return;
			}
		}
	}
}
