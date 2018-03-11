package com.ouc.cs.Lucas.collection;

import java.util.*;

public class IteratorTest {

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

}
