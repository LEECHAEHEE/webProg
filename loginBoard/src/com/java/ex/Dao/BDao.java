package com.java.ex.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.java.ex.Dto.BDto;


public class BDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	static final int DB_ERROR = 0;
	static final int WRITE_SUCCESS = 1;
	static final int WRITE_FAILED = 2;
	
	
	private BDao () {}
	private static class holder{
		private static final BDao instance = new BDao();
	}
	public static BDao getInstance() {
		return holder.instance;
	}
	
	public int write(String id, String name, String title, String content) {
		int writeResult = DB_ERROR;
		String sql = "insert into board (num,name,title,content,hit) values(board_seq.nextval, ?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setInt(4, 0);
			
			writeResult = pstmt.executeUpdate();
			if(writeResult==1) {
				writeResult = WRITE_SUCCESS;
			}else
				writeResult= WRITE_FAILED;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			streamCloser();
		}
		return writeResult;
	}
	
	
	public ArrayList<BDto> BListCommand(){
		ArrayList<BDto> dtos = new ArrayList<>();
		String sql = "select * from board";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String rDate = new SimpleDateFormat("MM/dd/HH/mm/ss").format(rs.getTimestamp("rdate"));
				String hit = String.valueOf(rs.getInt("hit"));
					
				BDto dto = new BDto(num, name, title, rDate, hit);
				dtos.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			streamCloser();
		}
		return dtos;
	}
	public void streamCloser() {
		try {
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection connection =null;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
			connection = dataSource.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
