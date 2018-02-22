package com.ouc.FeatureOfObject;

public class TestToString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog d = new Dog();
		System.out.println("d:=" + d);    //默认方法返回的是 类名 + hashCode   com.ouc.FeatureOfObject.TestToString$Dog@15db9742
	}
	
	static class Dog{
		/*
		public String toString(){
			return "I am a cool Dog";
		}
		*/
	}

}
