package com.ouc.cs.Lucas.Thread;

/*
 * wait() �Լ�����
 * notify() ���˻���
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
			}      //wait()����������Object,һ��wait,���Ͳ����ڸ��߳���
		}
		this.notify();
		*/
		//ʹ��if�ǳ�Σ�գ����this.wait()����ϣ���ô���˳�try()�飬�����̳߳��ִ���
		while(index == arrBread.length){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      //wait()����������Object,һ��wait,���Ͳ����ڸ��߳���
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
		//�����notify����ô��������������������������һ���߳�
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
			System.out.println("������" + b);
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
			System.out.println("������" + b);
			try{
				Thread.sleep((long)Math.random() * 1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}