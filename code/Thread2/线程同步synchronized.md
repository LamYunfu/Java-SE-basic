# 线程同步synchronized

并发：**同一个对象多个线程同时操作**

**数据不一致**：不同用户看同一个资源得到的结果不一样。

## 线程不安全

**抢票出现负数**

资源临界->多个线程进入资源->修改资源数目->出现负数

**出现相同票数**

每一个线程都有自己的工作空间用于和主存的数据进行交互，和主存的数据进行拷贝与覆盖，当主存的数据没有加锁，不同的线程的数据和主存之间发生了重复读，造成了主存的数据修改覆盖。

```
账户里面有100块：
线程a拷贝主存的100块到工作空间
线程b拷贝主存的100块到工作空间
线程a取走90块，将100改为10
线程a将10写回主存
线程b取走80块，将100改为20
线程b将20写回主存

结果：
主存显示为20块
但是一共取出了 170块
```

**线程不安全的例子**

```java
List<String> list = new ArrayList<String>();
for(int i = 0;i < 10000;i++){
    new Thread(()->{
        list.add(Thread.currentThread().getName());    
    }).start();
}
System.out.println(list.size);
```

最后结果为9987

个人估计list的size变量是list的一个类变量，当不同的线程在修改size的时候发生了数据覆盖。

---

## 线程同步

处理多线程问题时，多个线程访问同一个对象，并且某些线程还想要修改这个对象。这个时候，我们就需要用到“线程同步”。线程同步其实就是一种等待机制，多个需要同时访问此对象的线程进入这个对象的**等待池形成队列**，等待前面的线程使用完毕以后，下一个线程再使用。

为了解决访问冲突的问题，线程中引入*锁机制（synchronized）*,当一个线程获得对象的排他锁，独占资源，其它线程必须等待，使用后释放锁即可。存在以下问题：

* 一个线程持有锁会导致其它所有需要此锁的线程挂起；
* 在多线程的竞争下，加锁、释放锁会导致比较多的上下文切换和调度时延，引起性能问题。
* 如果一个优先级高的线程等待优先级低的线程释放锁会导致优先级倒置，引起性能问题。



synchronized关键字包括两种方法：synchronized方法和synchronized块

由于synchronized方法有的时候锁的范围过大，导致效低下，所以出现了synchronized块，提高锁的精准度，用于提高效率。

```java
public synchronized void run() {		//锁的是操作的对象的资源this
		// TODO Auto-generated method stub
		while(true) {
			if(ticketsNum == 0) {
				break;
			}
			System.out.println(Thread.currentThread().getName() + ":" + ticketsNum--);
		}
	}
```



#### 同步块

同步块：

```java
synchronized (obj){
    
}
```

obj称为同步监视器。

* obj可以是任何对象，但是推荐使用共享资源作为同步监视器。
* 同步方法中无需指定同步监视器，因为同步方法的同步监视器是this即该对象本身或者是class.



同步监视器的执行过程：

* 第一个线程访问，锁定同步监视器，执行其中的代码。
* 第二个线程访问，发现同步监视器被锁定，无法访问。
* 第一个线程访问完毕，解锁同步监视器。
* 第二个线程访问，发现同步监视器未被锁定，锁定并访问。

```java
synchronized(account){
    if(account.money - drawingMoney < 0){
        return;
    }
    ...取钱
}
```

```java
List<String> list = new ArrayList<String>();
for(int i = 0;i < 10000;i++){
    new Thread(()->{
        synchronized(list){
            list.add(Thread.currentThread().getName()); 
        }  
    }).start();
}
System.out.println(list.size);
```

