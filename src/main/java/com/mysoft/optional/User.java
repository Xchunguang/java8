package com.mysoft.optional;

import java.util.Optional;

public class User {

	public Integer age;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Optional<Integer> getAgeForNull(){
		Optional<Integer> optional = Optional.ofNullable(this.age);
		return optional;
	}
	
	
}
