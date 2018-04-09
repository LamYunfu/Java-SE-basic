package com.ouc.cs.Lucas.Thread;

public class TestJoin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread2 t1 = new MyThread2("abcde");
		t1.start();
		try{
			t1.join();				//�ϲ����̺߳�t1�߳�
		}catch(InterruptedException e){
			//join()����Ҳ���׳�InterruptedException
		}
		
		for(int i = 0;i < 10;i++){
			System.out.println("I am main thread");
		}
	}

}

class MyThread2 extends Thread{
	MyThread2(String s){
		super(s);   				//�����߳����ƽ��й���
	}
	
	public void run(){
		for(int i = 0;i < 10;i++){
			System.out.println("I am " + getName());       //Thread��ľ�̬���������ص�ǰ�̵߳�����
		}
		try{
			sleep(1000);
		}catch(InterruptedException e){
			return;
		}
	}
}