# Map接口

* 实现Map接口的类用来存储键--值对
* Map接口的实现类有HashMap和TreeMap等
* Map中key不能重复（直接比较hashCode,所以改写了equals的方法也要改写hashCode方法）,value可以重复

```java 
Object put(Object key, Object value);		//如果put的key在容器内已有，返回被替换的value值
//要求key和value都是对象类型，但是JDK1.5以后可以直接传入基本类型，JVM会自动对其打包成对象类型
Object get(Object key);
Object remove(Object key);
boolean containsKey(Object key);
boolean containsValue(Object value);
int size();
boolean isEmpty();
void putAll(Map t);
void clear();
```



### 试例

```java
import java.util.*;
public class MapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map m1 = new HashMap();
		Map m2 = new TreeMap();
		m1.put("one", new Integer(1));
		//m1.put("one", 1);			//JDK1.5以后拥有了自动打包的功能，扔进去的依然是对像
		m1.put("two", new Integer(2));
		m1.put("three", new Integer(3));
		m2.put("A", new Integer(1));
		m2.put("B", new Integer(2));
		System.out.println(m1.size());
		System.out.println(m1.containsKey("one"));
		System.out.println(m2.containsValue(new Integer(2)));
		if(m1.containsKey("two")){
			int i = ((Integer)m1.get("two")).intValue();
			System.out.println(i);
		}
		Map m3 = new HashMap(m1);
		m3.putAll(m2);
		System.out.println(m3);
	}

}

```

