package com.first.com.dto;

public class MDto {
	String name;
	int age;
	String gender;
	String id;
	String pw;
	String email;
	
	public MDto() {
		
	}
	
	public MDto(String name, int age, String gender, String id, String pw, String email) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.id = id;
		this.pw = pw;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
