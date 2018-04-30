package com.first.com.dao;

import java.sql.Connection;

public class MDao {
	
	/*****************************************************************
	 * 최초 한번만 DB에 연결하기 위해 singleton pattern 을 이용한다. *
	 * singleton pattern은 
	 *****************************************************************/
	private static Connection connection = new Connection();
	
	public Connection getInstance() {
		
	}
	public int loginCheck(String id, String pw) {
		
	}
}
