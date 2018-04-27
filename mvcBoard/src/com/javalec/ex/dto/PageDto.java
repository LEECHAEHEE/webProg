package com.javalec.ex.dto;

public class PageDto {
	private int totalCount;			//��ü �Խù� ��
	private int currPage;			//���� ȭ�鿡 ��Ÿ�� ������
	private int contentNum=10;		//�� ȭ�鿡 ��Ÿ�� �Խù� ��, �ʱ� 10
	private int startPage=1;		//�� ��Ͽ��� ù��° ������
	private int endPage=10;			//�� ��Ͽ��� ������ ������
	private int currBlock=1;		//���� ���
	private int endBlock;			//������ ���
	private boolean isPrev=false;	//�� ó�� ����ΰ�
	private boolean isNext;			//�� ������ ����ΰ�
	
	/*��ü ������ ���� ���ϴ� �޼ҵ�*/
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
		/* �� ��Ͽ��� ù��° ������ ���ϱ�
		 * 2��° ���(11) 		->	(2-1) * 10 + 1
		 * 13��° ���(121) 	->	(13-1) * 10 +1
		 * 30��° ���(291)		->	(30-1) * 10 +1
		 * ��, (������-1) * ���������� ��Ÿ�� �Խñ� + 1
		 * */
		this.startPage = (currPage-1)*10 + 1;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int lastBlock, int currBlock) {
		/* �� ��Ͽ��� ������ ������ ���ϱ�
		 * 2��° ���(20) 		->	2 * 10
		 * 13��° ���(130) 	->	13 * 10
		 * 30��° ���(300)		->	30* 10
		 * ��, ������ * ���������� ��Ÿ�� �Խñ�
		 * Ȥ��, getStartPage() + 9
		 * 
		 * �߿�)������ ����� ��� ������ ��������ȣ 
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
		 * ���� ��� / �� �������� ��Ÿ�� �Խñ�
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
