package com.ouc.cs.Lucas.Thread;

public class TestSync implements Runnable{
	Timer timer = new Timer();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestSync test = new TestSync();
		Thread t1 = new Thread(test);
		Thread t2 = new Thread(test);
		t1.setName("t1");
		t2.setName("t2");
		t1.start();
		t2.start();
	}
	
	public void run(){
		timer.add(Thread.currentThread().getName());
	}

}

class Timer{
	private static int num = 0;
	public synchronized void add(String name){        //执行该方法的过程中，当前对象被锁定
		synchronized(this){				//锁定当前对象，不可被其它线程打断
			num++;
			try{
				Thread.sleep(1);
			}catch(InterruptedException e){}
			System.out.println(name + ", 你是第" + num + "个使用Timer的线程");
		}
	}
}