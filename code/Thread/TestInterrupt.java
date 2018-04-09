package com.ouc.cs.Lucas.Thread;

import java.util.Date;

public class TestInterrupt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread thread = new MyThread();
		thread.start();
		try{
			Thread.sleep(10000);    //sleep是Thread类的静态方法，在哪个线程里面调用Thread方法，就让哪一个线程睡眠
		}catch(InterruptedException e){
		}
		/*
		 * 调用thread的interrupt()方法,给线程thread设置一个中断标志
		 * thread的sleep,wait等方法会检测这个标志位，同时抛出InterruptedException，并清除线程中的标志位
		 */
		thread.interrupt(); 
	}

}

class MyThread extends Thread{
	public void run(){
		while(true){
			System.out.println("===" + new Date() + "===");
			try{
				sleep(1000);				//每隔一秒钟，打印一次系统时间
			}catch(InterruptedException e){
				return;
			}
		}
	}
}
