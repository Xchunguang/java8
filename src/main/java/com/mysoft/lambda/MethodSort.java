package com.mysoft.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

public class MethodSort {

	public static void main(String[] args) {
		User user1 = User.create(User::new);
		user1.setAge(22);
		
		User user2 = User.create(User::new);
		user2.setAge(12);
		
		List<User> list = new ArrayList<User>();
		list.add(user1);
		list.add(user2);
		
		//java 8 方法应用的写法
		list.sort(Comparator.comparing(User::getAge));
		list.forEach(e->{
			System.out.println(e.getAge());
		});
		
		//java 8 lambda的写法
		list.sort((User o1,User o2)->o2.getAge()-o1.getAge());
		list.forEach(e->{
			System.out.println(e.getAge());
		});
	}
	
	public static class User{
		public Integer age;

		public static User create(Supplier<User> supplier){
			return supplier.get();
		}
		
		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}
		
	}
}
