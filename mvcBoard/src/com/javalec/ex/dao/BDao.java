package com.javalec.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.ex.dto.BDto;

public class BDao {
	DataSource dataSource;

	/* 생성자에서 DB 드라이버로드 DBCP 이용
	 * context.xml에 <Resource/> 등록 
	 * dbcp.jar WEB-INF/lib에 등록.
	 */
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 입력받은 게시판 내용을 DB에 저장.
	 * */
	public void write(String bName, String bTitle, String bContent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "insert into mvc_board values(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			int ri = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}//write
	
	/*
	 * DB에 연결해서 저장되어있는 게시판 내용들을 ArrayList<BDto> 에 담아서 반환.
	 */
	public ArrayList<BDto> list(){
		ArrayList<BDto> dtos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select from mvc_board order by bGroup desc, bStep asc";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate =rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit"); 
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep , bIndent);
				dtos.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}catch(Exception e2) {
				e2.getStackTrace();
			}
		}
		
		return dtos;
	}
	
	public BDto contentView(String strID) {
		
	}
	
	public void modify(String bID, String bName, String bTitle, String bContent) {
		
	}
	
	
	
	
}
