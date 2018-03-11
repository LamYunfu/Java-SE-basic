# List接口

* List接口是Collection的子接口，实现List接口的容器类的中的元素都是有顺序的
* List分为LinkedList和ArrayList两种，分别利用链表和数组作为底层的相关实现

### java.util.Collections类

* 封装了关于List容器的一系列的常用算法
  * void sort(List)	对List容器内的内容进行排序
  * void shuffle(List)       对List容器里的内容进行随机排序
  * void reverse(List)          对List容器里的对象逆序
  * void fill(List, Object)     用一个特定的对象重写整个List容器
  * void copy(List dest, List src)       将src容器里面的内容拷贝到dest容器当中去
  * int binarySearch(List, Object)        对于顺序的List容器，采用折半查找的方法查找特定的对象



### Comparable接口

* *Collections.sort()*方法如何确定容器中对象的大小顺序？

* 所有可以排序的类都实现了*java.lang.Comparable*接口，Comparable接口中只有一个方法

  ```java 
  public int compareTo(Object obj);
  	返回 0 表示this == obj
  	返回正数 表示 this > obj
  	返回负数表示 this < obj
  ```



* 实现了*Comparable*接口的类通过实现*comparaTo*方法从而确定该类对象的排序方式

```java
class Name implements Comparable{
    ...
    @Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Name n = (Name)o;
		int lastCmp = lastName.compareTo(n.lastName);		//先按姓
		return 
				(lastCmp != 0 ? lastCmp :
					firstName.compareTo(n.firstName));		//后按名
	}
}
```

