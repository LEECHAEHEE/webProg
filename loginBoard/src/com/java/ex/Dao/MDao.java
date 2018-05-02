package com.java.ex.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.java.ex.Dto.MDto;

public class MDao {
	private final int DB_ERROR = -1;
	private final int LOGIN_SUCCESS = 1;
	private final int LOGIN_FAILED_ID = 2;
	private final int LOGIN_FAILED_PW = 3;

	private final int JOIN_SUCCESS = 1;
	private final int JOIN_FAILED = 2;
	
	private final int ID_EXISTED=0;
	private final int ID_NON_EXISTED=1;
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
	
	public ArrayList<Object> loginCheck(String id, String pw) {
		ArrayList<Object> result = new ArrayList<>();
		
		String sql = "select name, pw from members where id=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String DBpw = rs.getString("pw");
				String name = rs.getString("name");
				
				if(DBpw.equals(pw)) {
					result.add(LOGIN_SUCCESS);
					result.add(name);
				}else {
					result.add(LOGIN_FAILED_PW);
				}
			}else {
				result.add(LOGIN_FAILED_ID);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			streamCloser();
		}
		return result;
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
	
	public int idCheck(String id) {
		int idResult = DB_ERROR;
		String sql = "select * from members where id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				idResult =  ID_EXISTED;
			}else {
				idResult= ID_NON_EXISTED;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			streamCloser();
		}
		return idResult;
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
