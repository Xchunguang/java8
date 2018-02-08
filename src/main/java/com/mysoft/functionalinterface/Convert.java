package com.mysoft.functionalinterface;
@FunctionalInterface
public interface Convert<F,T> {

	T convert(F from);
	
	//可以包含静态方法
	public static void hasStaticMethod(){
		System.out.println("包含静态方法");
	}
	
	default void hasDefaultMethod(){
		System.out.println("包含默认方法");
	}
}
