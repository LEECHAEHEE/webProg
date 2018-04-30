package com.javalec.ex.dto;

public class PagingDTO{
	int totalCount;
	int startPage;		//한 화면에 나타나는 처음 페이지
	int endPage;		//한 화면에 나타나는 마지막 페이지
	int startCount;		//한 페이지에 나타나는 첫 게시물
	int endCount;		//한 페이지에 나타나는 마지막 게시물
	

	public PagingDTO(int totalCount, int startPage, int endPage, int startCount, int endCount) {
		super();
		this.totalCount = totalCount;
		this.startPage = startPage;
		this.endPage = endPage;
		this.startCount = startCount;
		this.endCount = endCount;
	}



}
