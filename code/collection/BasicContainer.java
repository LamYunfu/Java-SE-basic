package com.ouc.cs.Lucas.collection;

import java.util.*;

public class BasicContainer {

	public static void main(String[] args) {
		Collection c = new HashSet();
		c.add("hello");
		c.add(new Name("f1","f2"));
		c.add(new Integer(100));
		c.remove("hello");   //String������д��equals����������������Ƴ�
		c.remove(new Integer(100));		//Integer��Ҳ��д��equals������
		c.remove(new Name("f1", "f2"));			//��Ҫ�Ƴ���Ҫ��дequals��hashCode����
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
		return firstName + "��" + lastName;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Name){
			Name name = (Name)obj;
			return (this.firstName.equals(name.firstName)) &&
					(this.lastName.equals(name.lastName));
		}
		return super.equals(obj);				//���򽻸�����ȥ����
	}
	/*
	 * �������Ķ�����Ϊ��������ֵ�ԣ���ʱ�򣬻�ʹ��hashCode()����,������ʶ�������
	 * �����ڱȽ����������ʱ�����ֱ�ӱȽ������������hashCode
	 * �Դ�����߱Ƚϵ�Ч��
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		return firstName.hashCode();		//�Լ�ʵ��һ���Ƚ��鷳������ֱ����һ���ֳɵ�
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