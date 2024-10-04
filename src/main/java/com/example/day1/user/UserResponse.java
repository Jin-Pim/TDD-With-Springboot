package com.example.day1.user;

public class UserResponse {
	private int id;
	private String firstName;
	private String lastName;
	private int age;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserResponse{" +
				"id'" + id + '\'' +
				" firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", age=" + age +
				'}';
	}
}