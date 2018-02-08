package com.mysoft.lambda;

import java.util.Arrays;
import java.util.List;

public class LambdaTest {

	public static void main(String[] args) {
//		最简单的形式
		Arrays.asList( 1, 2, 3 ).forEach( e -> System.out.println( e ) );
		
//		Lambda可以引用类的成员变量与局部变量
		String str = ",";
		Arrays.asList( 4, 5, 6 ).forEach( 
		    ( Integer e ) -> System.out.print( e + str ) );	
		
		List<Integer> list = Arrays.asList( 4, 2, 1,3);
		list.sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
		list.forEach(e->System.out.println(e));	
		
		
	}
}
