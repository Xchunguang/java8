package com.mysoft.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MethodUse {

	public static void main(String[] args) {
		//构造器引用
		Car car = Car.create( Car::new );
//		Car car = Car.create(()->{
//			return new Car();
//		});
		List< Car > cars = Arrays.asList( car );
		System.out.println(cars.size());
		
		//引用是静态方法,这个方法接受一个Car类型的参数
		cars.forEach( Car::collide );
		
		//引用是特定类的任意对象的方法,这个方法没有参数。
		cars.forEach( Car::repair );
		
		//引用是特定对象的方法引用,这个方法接受一个Car类型的参数
		Car police = Car.create( Car::new );
		cars.forEach( police::follow );
	}
	
	public static class Car {
		public static Car create( Supplier< Car > supplier ) {
			return supplier.get();
		} 

		public static void collide( Car car ) {
			System.out.println( "Collided " + car.toString() );
		}

		public void follow( Car another ) {
			System.out.println( "Following the " + another.toString() );
		}

		public void repair() { 
			System.out.println( "Repaired " + this.toString() );
		}
	}
}
