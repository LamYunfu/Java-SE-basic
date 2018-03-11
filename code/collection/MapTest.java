package com.ouc.cs.Lucas.collection;

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
