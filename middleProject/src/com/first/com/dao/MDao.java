package com.first.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*****************************************************************
 name, id, pw, email, address, rdate

	  ���� �ѹ��� DB�� �����ϱ� ���� singleton pattern �� �̿��Ѵ�. 
	  singleton pattern�� ������ Ŭ������, �����ڰ� �������� ȣ��� 
	  ���� ������ �����Ǵ� ��ü�� �ϳ��̰� ���� ���� ���Ŀ� ȣ���
	  �����ڴ� ������ �����ڰ� ������ ��ü�� �����Ѵ�. 			 
	  																	
	  --���� ���� ���Ǵ� initialization on demand holder idiom���--				 
	  jvm�� classLoader ��Ŀ����� class�� �ε�Ǵ� ������ �̿��� ���.
	  Lazy initialization����� �������鼭 Thread�� ����ȭ ���� �ذ�.
	  ��ø Ŭ���� Holder�� getInstance �ż��尡 ȣ��Ǳ� ������ ����
	  ���� ������, ���ʷ� getInstance() �޼��尡 ȣ��� �� Ŭ���� �δ�
	  �� ���� �̱��� ��ü�� �����Ͽ� �����Ѵ�. Holder�ȿ� ����� instance
	  �� static �̱� ������ Ŭ���� �ε� ������ �ѹ��� ȣ��Ǹ�,
	  final�� �Ἥ �ٽ� ���� �Ҵ� ���� �ʴ´�.  
*****************************************************************/


public class MDao {
	
	public static final int DB_ERROR = -1;
	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_FAILED_PW = 2;
	public static final int LOGIN_FAILED_ID = 3;

	
	Connection conn = null;
	PreparedStatement pstmt= null;
	ResultSet rs = null;
	
	
	private MDao() { }
	
	private static class LazyHolder{
		private static final MDao instance = new MDao();
	}
	public static MDao getInstance() {
		return LazyHolder.instance;
	}

	/* �α��� Ȯ�� */
	public int loginCheck(String id, String pw) {
		int result = DB_ERROR;
		String sql = "select pw from members where id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String DBrs = rs.getString("pw");
				if(DBrs.equals(pw)) {
					//login success
					result = LOGIN_SUCCESS;
				}else {
					//password incorrect
					result = LOGIN_FAILED_PW;
				}
			}else {
				//row not existed
				result = LOGIN_FAILED_ID;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/* DB���� */
	private Connection getConnection () { 
		Connection conn = null;
		Context context = null;
		DataSource dataSource = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			conn = dataSource.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
}
