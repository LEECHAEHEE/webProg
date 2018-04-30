package com.first.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*****************************************************************
 name, id, pw, email, address, rdate

	  최초 한번만 DB에 연결하기 위해 singleton pattern 을 이용한다. 
	  singleton pattern을 따르는 클래스는, 생성자가 여러차례 호출되 
	  더라도 실제로 생성되는 객체는 하나이고 최초 생성 이후에 호출된
	  생성자는 최초의 생성자가 생성한 객체를 리턴한다. 			 
	  																	
	  --가장 많이 사용되는 initialization on demand holder idiom기법--				 
	  jvm의 classLoader 매커니즘과 class가 로드되는 시점을 이용한 방법.
	  Lazy initialization방식을 가져가면서 Thread간 동기화 문제 해결.
	  중첩 클래스 Holder는 getInstance 매서드가 호출되기 전에는 참조
	  되지 않으며, 최초로 getInstance() 메서드가 호출될 때 클래스 로더
	  에 의해 싱글톤 객체를 생성하여 리턴한다. Holder안에 선언된 instance
	  가 static 이기 때문에 클래스 로딩 시점에 한번만 호출되며,
	  final을 써서 다시 값이 할당 되지 않는다.  
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

	/* 로그인 확인 */
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

	/* DB접속 */
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
