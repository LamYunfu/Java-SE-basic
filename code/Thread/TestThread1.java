package com.ouc.cs.Lucas.Thread;
/*
 * 调用Start方法，Main Thread和自己定义的Runner1线程交替运行
 * 直接调用run方法，属于方法调用则是子线程完全执行完才会返回到父线程执行父线程的方法。
 */
public class TestThread1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runner1 r = new Runner1();
		r.start();
//		r.run();
//		Thread t = new Thread(r);			//要求传入实现了Runnable接口的对象
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
