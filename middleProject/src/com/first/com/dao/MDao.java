package com.first.com.dao;

import java.sql.Connection;

public class MDao {
	
	/*****************************************************************
	 * ���� �ѹ��� DB�� �����ϱ� ���� singleton pattern �� �̿��Ѵ�. *
	 * singleton pattern�� 
	 *****************************************************************/
	private static Connection connection = new Connection();
	
	public Connection getInstance() {
		
	}
	public int loginCheck(String id, String pw) {
		
	}
}
