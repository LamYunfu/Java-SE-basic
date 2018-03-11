# Iterator接口

* 所有实现了Collection接口的容器类都有一个iterator方法用以返回一个实现了Iterator接口的**对象**。

  不同的容器各自实现iterator接口中的方法，调用时返回不同的实现了iterator的对象。

* Iterator对象称作迭代器，用以方便地实现对容器内元素的遍历操作。

* 三个方法

  * **hasNext()**

    *return true if the iteration has more elements.*

  * **next()**

  * **remove()**

```java
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Collection c = new HashSet();
		c.add(new Name("f1", "l1"));
		c.add(new Name("f2", "l2"));
		c.add(new Name("f3", "l3"));
		Iterator i = c.iterator();
		while(i.hasNext()){
			//next()返回的值是Object类型，需要转换成相应的类型
			Name n = (Name)i.next();
			System.out.println(n.getLastName() + " ");
		}
	}
```



### Iterator的remove()方法

* Iterator对象的remove方法时在迭代过程中删除元素的唯一的安全方法。
* 在iterator迭代过程中，只能使用iterator对象的remove方法，而不能使用原来容器的remove方法，因为iterator方法对它进行了锁定。