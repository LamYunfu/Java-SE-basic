package com.ouc.cs.Lucas.Thread;

/*
 * wait() 自己挂起
 * notify() 别人唤醒
 */
public class ProducerConsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SyncStack ss = new SyncStack();
		Producer p = new Producer(ss);
		Consumer c = new Consumer(ss);
		new Thread(p).start();
		new Thread(c).start();
		
	}

}

class Bread{
	int id;
	 Bread(int id){
		 this.id = id;
	 }
	 
	 public String toString(){
		 return "Bread: " + id;
	 }
}

class SyncStack{
	int index = 0;
	Bread[] arrBread = new Bread[6];
	
	public synchronized void push(Bread b){
		/*
		if(index == arrBread.length){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      //wait()方法来自于Object,一旦wait,锁就不属于该线程了
		}
		this.notify();
		*/
		//使用if非常危险，如果this.wait()被打断，那么会退出try()块，唤醒线程出现错误
		while(index == arrBread.length){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      //wait()方法来自于Object,一旦wait,锁就不属于该线程了
		}
		this.notify();
		arrBread[index] = b;
		index++;
	}
	
	public synchronized Bread pop(){
		if(index == 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//如果不notify，那么程序会陷入死锁，这里叫醒另外一个线程
		this.notify();			//Wakes up a single thread that is waiting on this object's monitor.
		return arrBread[--index];
	}
}

class Producer implements Runnable{
	SyncStack bascket = null;
	Producer(SyncStack ss){
		this.bascket = ss;
	}
	
	public void run(){
		for(int i = 0;i < 20;i++){
			Bread b = new Bread(i);
			bascket.push(b);
			System.out.println("生产了" + b);
			try{
				Thread.sleep((long) Math.random() * 200);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable{
	SyncStack bascket = null;
	Consumer(SyncStack ss){
		this.bascket = ss;
	}
	
	public void run(){
		for(int i = 0;i < 20;i++){
			Bread b = bascket.pop();
			System.out.println("消费了" + b);
			try{
				Thread.sleep((long)Math.random() * 1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}