package com.java.ex.Dto;


public class BDto {
	private int num;
	private String name;
	private String title;
	private String content;
	private String rDate;
	
	public BDto() {
		// TODO Auto-generated constructor stub
	}
	
	public BDto(int num, String name, String title, String content) {
		super();
		this.num = num;
		this.name = name;
		this.title = title;
		this.content = content;
	}
	
	public BDto(int num, String name, String title, String content, String rDate) {
		super();
		this.num = num;
		this.name = name;
		this.title = title;
		this.content = content;
		this.rDate = rDate;
	}
	
	public int getnum() {
		return num;
	}
	public void setnum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBdate() {
		return rDate;
	}
	public void setBdate(String bdate) {
		this.rDate = bdate;
	}
	
	
	
}
