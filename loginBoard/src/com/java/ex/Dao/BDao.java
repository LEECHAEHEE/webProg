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
	/********************************************************************************************************************
	 * GETTOTALLIST
	********************************************************************************************************************/
	public int getTotalList() {
		int totalList = 0;
		
		String sql = "select count(*) as totalList from board";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalList = rs.getInt("totalList");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			streamCloser();
		}
		return totalList;
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
	 * BListCommand(*****)
	********************************************************************************************************************/
	public ArrayList<BDto> BListCommand(int curPage){
		ArrayList<BDto> dtos = new ArrayList<>();
		String sql = "select a.rnum, a.id, a.name, a.num, a.title, a.rdate, a.hit\r\n" + 
				"from(   select rownum as rnum, b.id, b.name, b.num, b.title, b.rdate, b.hit\r\n" + 
				"        from(   select id, name, num, title, rdate, hit\r\n" + 
				"                from board\r\n" + 
				"                order by num desc)b\r\n" + 
				"        where rownum <=?)a\r\n" + 
				"where a.rnum>=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, curPage*10);
			pstmt.setInt(2, curPage*10-9);
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
		String sql2 = "update board set hit=hit+1 where num=?";
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
				if(pstmt!=null) pstmt.close();
			}
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
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
			if(conn!=null) conn.close();
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
