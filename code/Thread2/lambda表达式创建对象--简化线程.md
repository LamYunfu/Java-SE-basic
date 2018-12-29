# lambda表达式创建对象--简化线程

```java
()->{
	for(int i = 0;i < 20;i++) {
		System.out.println("一边听歌");
	}
}
```

* 接口->匿名内部类->lambda
* 作用：**避免匿名内部类定义过多**。
* 其实质属于函数式编程。
* 推导过程：外部类->静态内部类->局部内部类->匿名内部类->lambda表达式

```java
public class LambdaThread {
	//静态内部类
	static class Test implements Runnable{

		@Override
		public void run() {
			for(int i = 0;i < 20;i++) {
				System.out.println("一边听歌");
			}
		}
		
	}
	
	public static void main(String[] args) {
		new Thread(new Test()).start();
		//局部内部类
		class Test2 implements Runnable{

			@Override
			public void run() {
				for(int i = 0;i < 20;i++) {
					System.out.println("一边听歌");
				}
			}
			
		}
		new Thread(new Test2()).start();
		
		//匿名内部类 必须借助接口或者父类
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0;i < 20;i++) {
					System.out.println("一边听歌");
				}
			}			
		}).start();
		
		//jdk8 使用lambda简化,接口内只能有一个方法，要不然无法进行推导
		new Thread(()->{
			for(int i = 0;i < 20;i++) {
				System.out.println("一边听歌");
			}
		}).start();
	}
}

```

* 一般推导过程

```java

interface ILike{
	void lambda();
}
//外部类
class Like implements ILike{

	@Override
	public void lambda() {
		System.out.println("I like Lambda");
	}
	
}

public class LambdaTest {
	//静态内部类
	static class Like2 implements ILike{
		@Override
		public void lambda() {
			System.out.println("I like Lambda");
		}		
	}
	
	public static void main(String[] args) {
		ILike like = new Like();
		
		like = new Like2();
		like.lambda();
		
        //局部内部类
        class Like3 implements ILike{
			@Override
			public void lambda() {
				System.out.println("I like Lambda");
			}		
		}
       	like = new ILike();
     
		//匿名内部类
		like = new ILike() {
			public void lambda() {
				System.out.println("I like Lambda");
			}
		};
		
		//lambda 省去了接口名和接口内函数的名称，因此只允许接口内只有一个未实现的方法
		like = ()->{
			System.out.println("I like Lambda");
		};
	}
}
```

## 带参数的Lambda表达式

```java
interface ITest{
	void lambda(int a);
}

public class LambdaTest02 {
	
	public static void main(String[] args) {
		//在构造lambda表达式,创建对象实例的同时实现该接口的方法。
		ITest test = (int a)->{
			System.out.println("The parameter is :" + a);
		};
		test.lambda(100);
		
		//也可以省略参数类型
		ITest test1 = (a)->{
			System.out.println("The parameter is :" + a);
		};
		
		//甚至可以省略括号(当只有一个参数的时候)
		ITest test2 = a->{
			System.out.println("The parameter is :" + a);
		};
		
		//如果实现的接口中只有一行代码，甚至连花括号都可以不要
		ITest test3 = a->System.out.println("The parameter is :" + a);
		
	}
}
```

## 带返回值的Lambda表达式

```java
interface ITest{
	int lambda(int a, int b);
}
public class LambdaTest03 {

	public static void main(String[] args) {
		ITest test = (int a,int b)->{
			System.out.println("The firse parameter is " + a + "The second parameter is" + b);
			return (a + b);
		};
		test.lambda(100, 200);
		
		ITest test1 = (a,b)->{
			System.out.println("The firse parameter is " + a + "The second parameter is" + b);
			return (a + b);
		};
		
		ITest test2 = (a,b)-> a+b;
	}

}
```

## Lambda表达式创建线程究极奥义

```java
public static void main(String[] args) {
	new Thread(()->{
		System.out.println("一边学习Lambda");
	}).start();
		
	new Thread(()-> System.out.println("一边泪流满面")).start();
}
```

