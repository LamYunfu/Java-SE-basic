package com.ouc.cs.Lucas.collection;

import java.util.*;

public class SetTest {
	public static void main(String[] args) {
		Set s1 = new HashSet();
		Set s2 = new HashSet();
		s1.add("a");
		s1.add("b");
		s1.add("c");
		s2.add("d");
		s2.add("a");
		s2.add("b");
		
		Set sn = new HashSet(s1);
		sn.retainAll(s2);			//取交集
		Set su = new HashSet(s1);
		su.addAll(s2);			//把s2中多出的部分添加
		System.out.println(sn);
		System.out.println(su);
	}
}
