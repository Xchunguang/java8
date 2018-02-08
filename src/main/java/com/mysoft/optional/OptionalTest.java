package com.mysoft.optional;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		
		//允许为空值，当值为空时也不会报错
		Optional< String > name = Optional.ofNullable( null );
		System.out.println( "name是否有值 " + name.isPresent() );        
		System.out.println( "如果名称为空: " + name.orElseGet( () -> "代替名称" ) ); 
		System.out.println( name.map( s -> "名称为： " + s  ).orElse( "名称为空" ) );
		
		//不允许为空值，当值为空时会报错
		Optional< String > firstName = Optional.of( "name" );
		System.out.println( "firstName是否有值 " + firstName.isPresent() );        
		System.out.println( "如果名称为空: " + firstName.orElseGet( () -> "代替名称" ) ); 
		System.out.println( firstName.map( s -> "名称为： " + s  ).orElse( "名称为空" ) );
		
		
		/**
		 * 避免空指针错误的处理
		 */
		User user = new User();
		//通过返回Optional类型避免发生空指针错误影响程序执行
		int age1 = user.getAgeForNull().orElseGet(()->new Integer(0));
		System.out.println(age1);
		
		//由于没有给user对象的属性赋值，所以会报错 java.lang.NullPointerException
		int age = user.getAge();
		System.out.println(age);
		
	}
}
