package com.ouc.cs.Lucas.Thread;

public class TestJoin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread2 t1 = new MyThread2("abcde");
		t1.start();
		try{
			t1.join();				//合并主线程和t1线程
		}catch(InterruptedException e){
			//join()方法也会抛出InterruptedException
		}
		
		for(int i = 0;i < 10;i++){
			System.out.println("I am main thread");
		}
	}

}

class MyThread2 extends Thread{
	MyThread2(String s){
		super(s);   				//传入线程名称进行构造
	}
	
	public void run(){
		for(int i = 0;i < 10;i++){
			System.out.println("I am " + getName());       //Thread类的静态方法，返回当前线程的名字
		}
		try{
			sleep(1000);
		}catch(InterruptedException e){
			return;
		}
	}
}