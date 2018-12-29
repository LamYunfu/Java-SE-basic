# Java线程

juc并发编程

## 简介

* 多任务是多线程的初衷
* 一个进程可以有多个线程，程序是一个静态的概念，进程是一个动态的概念。

## 线程创建

### 创建线程的三种方式

* 继承Thread类
* 实现Runnable接口
* 实现Callable接口（位于juc并发包下，使用不多）

#### 继承Thread类

* 明一个类的一个子类`Thread`。这个子类应该覆盖`run` `Thread`类的方法。

```java
class PrimeThread extends Thread {
         long minPrime;
         PrimeThread(long minPrime) {
             this.minPrime = minPrime;
         }

         public void run() {
             // compute primes larger than minPrime
              . . .
         }
     }
```

下面的代码将创建一个线程,并开始运行:

> ```java
>      PrimeThread p = new PrimeThread(143);
>      p.start();
>  
> ```

#### 实现Runnable接口

另一种方法来创建一个线程是声明一个类实现`Runnable`接口。这个类实现了`run`方法。类的实例可以分配,通过创建`Thread`时作为参数,并开始。同样的例子在这其他风格看起来像下面的:

------

> ```java
>      class PrimeRun implements Runnable {
>          long minPrime;
>          PrimeRun(long minPrime) {
>              this.minPrime = minPrime;
>          }
> 
>          public void run() {
>              // compute primes larger than minPrime
>               . . .
>          }
>      }
>  
> ```

------

下面的代码将创建一个线程,并开始运行:

> ```java
>      PrimeRun p = new PrimeRun(143);
>      new Thread(p).start();
> ```

由于Runnable接口中并没有实现start方法，因此需要创建一个Thread类的对象用于启动线程。

**run方法和start方法的区别**

* run方法只是普通的方法调用，并没有启动新的线程，得出的结果一定是顺序执行的。
* start方法启动了新的线程，交给cpu调度，加入到调度器中，它与主线程的调度时机由cpu所决定。

#### 实现callable接口

实现callable接口，重写call方法。