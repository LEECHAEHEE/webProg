package com.javalec.ex.dto;

public class PageDto {
	private int totalCount;			//전체 게시물 수
	private int currPage;			//현재 화면에 나타난 페이지
	private int contentNum=10;		//한 화면에 나타낼 게시물 수, 초기 10
	private int startPage=1;		//한 블록에서 첫번째 페이지
	private int endPage=10;			//한 블록에서 마지막 페이지
	private int currBlock=1;		//현재 블록
	private int endBlock;			//마지막 블록
	private boolean isPrev=false;	//맨 처음 블록인가
	private boolean isNext;			//맨 마지막 블록인가
	
	/*전체 페이지 수를 구하는 메소드*/
	public int getTotalPage(int totalCount, int contentNum) {
		int totalPage = totalCount/contentNum;
		if(totalCount%contentNum>0) totalPage++;
		if(totalPage < this.currPage) currPage = totalPage;
		return totalPage;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getContentNum() {
		return contentNum;
	}
	public void setContentNum(int contentNum) {
		this.contentNum = contentNum;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartpage(int currPage) {
		/* 한 블록에서 첫번째 페이지 구하기
		 * 2번째 블록(11) 		->	(2-1) * 10 + 1
		 * 13번째 블록(121) 	->	(13-1) * 10 +1
		 * 30번째 블록(291)		->	(30-1) * 10 +1
		 * 즉, (현재블록-1) * 한페이지에 나타낼 게시글 + 1
		 * */
		this.startPage = (currPage-1)*10 + 1;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int lastBlock, int currBlock) {
		/* 한 블록에서 마지막 페이지 구하기
		 * 2번째 블록(20) 		->	2 * 10
		 * 13번째 블록(130) 	->	13 * 10
		 * 30번째 블록(300)		->	30* 10
		 * 즉, 현재블록 * 한페이지에 나타낼 게시글
		 * 혹은, getStartPage() + 9
		 * 
		 * 중요)마지막 블록일 경우 마지막 페이지번호 
		 * */
		if(lastBlock == currBlock) 
			this.endPage = getTotalPage(getTotalCount(), getContentNum());
		else
			this.endPage = getStartPage() + 9;
	}
	public int getCurrBlock() {
		return currBlock;
	}
	public void setCurrBlock(int pageNum) {
		/*
		 * 현재 블록 / 한 페이지에 나타낼 게시글
		 */
		this.currBlock = pageNum/contentNum;
		if( pageNum%contentNum > 0 ) currBlock++;
	}
	public int getEndBlock() {
		return endBlock;
	}
	public void setEndBlock(int totalCount) {
		this.endBlock = totalCount/(10*this.contentNum);
		if (totalCount % (10*this.contentNum)>0) endBlock++; 
	}
	public boolean isPrev() {
		return isPrev;
	}
	public void setPrev(boolean isPrev) {
		this.isPrev = isPrev;
	}
	public boolean isNext() {
		return isNext;
	}
	public void setNext(boolean isNext) {
		this.isNext = isNext;
	}
	
	public void prevnext(int currPage) {
		if( currPage > 0 && currPage < 11) {
			setPrev(false);
			setNext(true);
		}else if( getEndBlock() == getCurrBlock()) {
			setPrev(true);
			setNext(false);
		}else {
			setPrev(true);
			setNext(true);
		}
	}
}
