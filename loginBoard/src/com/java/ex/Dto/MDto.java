package com.java.ex.Dto;

import java.sql.Timestamp;

public class MDto {
	private String name;
	private String id;
	private String pw;
	private String email;
	private String address;
	private String date;
	
	public MDto() {
	}

	public MDto(String name, String id, String pw, String email, String address) {
		super();
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
