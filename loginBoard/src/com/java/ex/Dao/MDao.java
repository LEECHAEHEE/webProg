package com.java.ex.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.java.ex.Dto.MDto;

public class MDao {
	private final int DB_ERROR = -1;
	private final int LOGIN_SUCCESS = 1;
	private final int LOGIN_FAILED_ID = 2;
	private final int LOGIN_FALIED_PW = 3;

	private final int JOIN_SUCCESS = 1;
	private final int JOIN_FAILED = 2;
	private MDao () {}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private static class holder{
		private static final MDao instance = new MDao();
	}
	
	public static MDao getInstance() {
		return holder.instance;
	}
	
	public int loginCheck(String id, String pw) {
		int loginResult = DB_ERROR;
		String sql = "select pw from members where id=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String DBpw = rs.getString("pw");
				if(DBpw.equals(pw)) {
					loginResult = LOGIN_SUCCESS;
				}else {
					loginResult = LOGIN_FALIED_PW;
				}
			}else {
				loginResult = LOGIN_FAILED_ID;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			streamCloser();
		}
		return loginResult;
	}
	
	
	public int join(MDto dto) {
		int joinResult = DB_ERROR;
		String sql = "insert into members(name,id,pw,email,address) values(?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPw());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getAddress());
			
			joinResult = pstmt.executeUpdate(); 
			if(joinResult==1) {
				joinResult = JOIN_SUCCESS;
			}else {
				joinResult = JOIN_FAILED;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			streamCloser();
		}
		return joinResult;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		Context context = null;
		DataSource dataSource = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			conn = dataSource.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return conn;
	}
	public int idCheck() {
		return -1;
	}
	public void streamCloser() {
		try {
		if(pstmt!=null) pstmt.close();
		if(rs!=null) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
