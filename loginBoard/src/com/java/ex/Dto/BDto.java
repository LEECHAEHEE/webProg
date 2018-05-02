package com.java.ex.Dto;


public class BDto {
	private int num;
	private String name;
	private String title;
	private String content;
	private String rDate;
	private int hit;
	
	public BDto() {
		// TODO Auto-generated constructor stub
	}
	
	public BDto(int num, String name, String title, String rDate, int hit) {
		super();
		this.num = num;
		this.name = name;
		this.title = title;
		this.rDate = rDate;
		this.hit = hit;
	}
	
	public BDto(int num, String name, String title, String content, String rDate, int hit) {
		super();
		this.num = num;
		this.name = name;
		this.title = title;
		this.content = content;
		this.rDate = rDate;
		this.hit = hit;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
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

	public String getrDate() {
		return rDate;
	}

	public void setrDate(String rDate) {
		this.rDate = rDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	
	
	
}
