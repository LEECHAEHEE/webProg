package com.java.ex.Dto;

public class PDto {
	private int listPerPage = 10;	//한 페이지에 나타날 게시글 수	(기본 10개)
	private int PagePerView =10;	//한 화면에 나타날 페이지 수	(기본 10개)
	private int curPage=1;			//현재 페이지
	private int totalList;			//총 게시글 수
	private int totalPage;			//총 페이지 개수 
	private int startPage = 1;		//시작 페이지 (처음 페이지는 1)
	private int lastPage;			//마지막 페이지
	private boolean prev=false;
	private boolean next=true;
	/********************************************************************************************************************
	 * SET totalList
	********************************************************************************************************************/
	public PDto() {
		// TODO Auto-generated constructor stub
	}
	
	public PDto(int curPage,int totalList) {
		this.totalList = totalList;
		this.curPage = curPage;
		this.totalPage = getTotalPage();
		this.startPage = getStartPage();
		this.lastPage = getLastPage();
		
		if(curPage > 10) setPrev(true);
		else setPrev(false);
		
		if(curPage < (totalPage-1)/getPagePerView()*getPagePerView()+1) setNext(true);
		else setNext(false);
	}
	
	public PDto(int listPerPage, int pagePerView, int curPage, int totalList, int totalPage, int startPage,
			int lastPage) {
		super();
		this.listPerPage = listPerPage;
		PagePerView = pagePerView;
		this.curPage = curPage;
		this.totalList = totalList;
		this.totalPage = totalPage;
		this.startPage = startPage;
		this.lastPage = lastPage;
	}

	public int getListPerPage() {
		return listPerPage;
	}

	public void setListPerPage(int listPerPage) {
		this.listPerPage = listPerPage;
	}

	public int getPagePerView() {
		return PagePerView;
	}

	public void setPagePerView(int pagePerView) {
		PagePerView = pagePerView;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalList() {
		return totalList;
	}

	public void setTotalList(int totalList) {
		this.totalList = totalList;
	}

	public int getTotalPage() {
		totalPage = totalList/getListPerPage();
		if(totalList%getListPerPage()>0) totalPage++;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return ((curPage-1)/getPagePerView()*getPagePerView()+1);
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getLastPage() {
		lastPage = startPage+getPagePerView()-1;
		if(lastPage>getTotalPage()) lastPage = totalPage;
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
		
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}
}
