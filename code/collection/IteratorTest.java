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
			//next()���ص�ֵ��Object���ͣ���Ҫת������Ӧ������
			Name n = (Name)i.next();
			System.out.println(n.getLastName() + " ");
		}
	}

}
