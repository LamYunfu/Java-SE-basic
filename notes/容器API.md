# 容器API

### 容器类图结构

* Collection
  * Set 
    * HashSet
  * List 
    * LinkedList
    * ArrayList
* Map
  * HashMap

1. Set中装的数据没有顺序，并且不可重复
2. List中装的数据有顺序，并且可以重复
3. Map里面装的是键值对



### Collection方法举例

* 容器在调用remove, contains等方法的时候需要比较对象是否相等，这会涉及到对象类型的equals方法和hashCode方法；对于自定义的类型，需要重写equals方法和hashCode方法来实现自定义相等规则。**相等的对象应该具有相等的hash Codes**

```java
import java.util.*;

public class BasicContainer {

	public static void main(String[] args) {
		Collection c = new HashSet();
		c.add("hello");
		c.add(new Name("f1","f2"));
		c.add(new Integer(100));
		c.remove("hello");   //String方法重写了equals方法，所以这里会移除
		c.remove(new Integer(100));		//Integer类也重写了equals方法。
		c.remove(new Name("f1", "f2"));			//想要移除需要重写equals和hashCode方法
		System.out.println(c.remove(new Name("f1", "f2")));
		System.out.println(c);
	}

}

class Name{
	private String firstName, lastName;

	public Name(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String toString(){
		return firstName + "　" + lastName;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Name){
			Name name = (Name)obj;
			return (this.firstName.equals(name.firstName)) &&
					(this.lastName.equals(name.lastName));
		}
		return super.equals(obj);				//否则交给父类去处理
	}
	/*
	 * 当这个类的对向作为索引（键值对）的时候，会使用hashCode()方法,用来标识这个对象
	 * 这样在比较两个对象的时候可以直接比较这两个对象的hashCode
	 * 以此来提高比较的效率
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		return firstName.hashCode();		//自己实现一个比较麻烦，不如直接用一个现成的
	}
}
```

