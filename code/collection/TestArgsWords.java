package com.ouc.cs.Lucas.collection;

import java.util.HashMap;
import java.util.Map;

public class TestArgsWords {
//	private static final Integer ONE = new Integer(1);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map m = new HashMap();
		for(int i = 0;i < args.length;i++){
			//�����滻�������������ͬ�Ĳ����ĸ���
//			Integer freq = (Integer)m.get(args[i]);
			int freq = (int)m.get(args[i]);				//ȡ������Object����
//			m.put(args[i], (freq == null ? ONE : new Integer(freq.intValue() + 1)));
			m.put(args[i], (freq == 0 ? 1 : freq + 1));
		}
		System.out.println(m.size() + " distinct words detected");
		System.out.println(m);
	}

}
