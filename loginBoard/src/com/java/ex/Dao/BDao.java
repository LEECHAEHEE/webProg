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
import com.sun.org.apache.xml.internal.dtm.DTMDOMException;


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

	/********************************************************************************************************************
	 * WRITE
	********************************************************************************************************************/
	public int write(String id, String name, String title, String content) {
		int writeResult = DB_ERROR;
		String sql = "insert into board (num,id,name,title,content,hit) values(board_seq.nextval, ?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setInt(5, 0);
			
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
	
	/********************************************************************************************************************
	 * BListCommand
	********************************************************************************************************************/
	public ArrayList<BDto> BListCommand(){
		ArrayList<BDto> dtos = new ArrayList<>();
		String sql = "select * from board order by num desc";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String rDate = new SimpleDateFormat("MM/dd HH:mm:ss").format(rs.getTimestamp("rdate"));
				int hit = rs.getInt("hit");
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
	/********************************************************************************************************************
	 * CONTENT
	********************************************************************************************************************/
	public BDto content(String num) {
		BDto bDto = null;
		String sql = "select * from board where num=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String rDate = new SimpleDateFormat("MM/dd hh:mm:ss").format(rs.getTimestamp("rDate"));
				int hit= rs.getInt("hit");
				bDto = new BDto(Integer.parseInt(num), id, name, title, content, rDate, hit);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			streamCloser();
		}
		return bDto;
	}
	/********************************************************************************************************************
	 * MODIFY
	********************************************************************************************************************/
	public int modify(String num, String title, String content) {
		int modifyResult = DB_ERROR;
		String sql = "update board set title=?, content=? where num=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, num);
			modifyResult = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			streamCloser();
		}
		return modifyResult;
	}
	/********************************************************************************************************************
	 * MODIFY
	********************************************************************************************************************/
	public int delete(String num) {
		int deleteResult = DB_ERROR;
		String sql = "delete from board where num=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			deleteResult = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			streamCloser();
		}
		return deleteResult;
	}
	/********************************************************************************************************************
	 * SREAMCLOSER
	********************************************************************************************************************/
	public void streamCloser() {
		try {
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/********************************************************************************************************************
	 * GET CONNECTION
	********************************************************************************************************************/
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
