package com.ouc.cs.Lucas.collection;

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

class Name implements Comparable{
	private String firstName, lastName;

	public Name(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName(){
		return firstName;
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

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Name n = (Name)o;
		int lastCmp = lastName.compareTo(n.lastName);
		return 
				(lastCmp != 0 ? lastCmp :
					firstName.compareTo(n.firstName));
	}
}