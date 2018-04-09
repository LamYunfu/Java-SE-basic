package com.ouc.cs.Lucas.Thread;

public class TestYield {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread3 t1 = new MyThread3("t1");
		MyThread3 t2 = new MyThread3("t2");
		t1.start();
		t2.start();
	}

}

class MyThread3 extends Thread{
	MyThread3(String s){
		super(s);
	}
	public void run(){
		for(int i = 1;i <= 100;i++){
			System.out.println(getName() + i);
			if(i % 10 == 0){
				yield();		//每执行到10的整数倍次的时候就让出一次CPU让另外一个线程执行
			}
		}
	}
}