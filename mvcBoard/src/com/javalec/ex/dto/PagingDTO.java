package com.javalec.ex.dto;

public class PagingDTO{
	int totalCount;
	int startPage;		//�� ȭ�鿡 ��Ÿ���� ó�� ������
	int endPage;		//�� ȭ�鿡 ��Ÿ���� ������ ������
	int startCount;		//�� �������� ��Ÿ���� ù �Խù�
	int endCount;		//�� �������� ��Ÿ���� ������ �Խù�
	

	public PagingDTO(int totalCount, int startPage, int endPage, int startCount, int endCount) {
		super();
		this.totalCount = totalCount;
		this.startPage = startPage;
		this.endPage = endPage;
		this.startCount = startCount;
		this.endCount = endCount;
	}



}
