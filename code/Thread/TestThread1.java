package com.ouc.cs.Lucas.Thread;
/*
 * ����Start������Main Thread���Լ������Runner1�߳̽�������
 * ֱ�ӵ���run���������ڷ��������������߳���ȫִ����Ż᷵�ص����߳�ִ�и��̵߳ķ�����
 */
public class TestThread1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runner1 r = new Runner1();
		r.start();
//		r.run();
//		Thread t = new Thread(r);			//Ҫ����ʵ����Runnable�ӿڵĶ���
//		t.start();
		for(int i = 0;i < 100;i++){
			System.out.println("Main thread:-------" + i);
		}
	}

}

//class Runner1 implements Runnable{
class Runner1 extends Thread{
	public void run(){
		for(int i = 0;i < 100;i++){
			System.out.println("Runner1:" + i);
		}
	}
}
