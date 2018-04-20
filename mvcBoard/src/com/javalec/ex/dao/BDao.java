package com.javalec.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.ex.dto.BDto;


public class BDao {
	
	Context context = null;
	DataSource dataSource = null;
	
	public BDao() {
		// TODO Auto-generated constructor stub
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*�Խ��� ��� ����*/
	public ArrayList<BDto> showList() {
		ArrayList<BDto> dtos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from mvc_board order by bGroup desc, bStep asc";

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bID = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bGroup = rs.getInt("bGroup");
				int bHit = rs.getInt("bHit");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				BDto dto = new BDto(bID, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
			
			return dtos;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	/*�Խ��� �ۼ�*/
	public void write(String bName, String bTitle, String bContent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "insert into mvc_board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			int ri = pstmt.executeUpdate();
			
			if(ri == 1) {
				System.out.println("Completed to insert");
			}else {
				System.err.println("Failed to insert");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}//public void write(String bName, String bTitle, String bContent)
	
	/*�Խ��� �����ֱ�*/
	public BDto showContent(String bId) {
		upHit(String.valueOf(bId));
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BDto dto = null;
		String query = "select * from mvc_board where bId = ?";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int bID = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bGroup = rs.getInt("bGroup");
				int bHit = rs.getInt("bHit");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				dto = new BDto(bID, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}else {
				System.out.println("err;BDao.java : there is no data with " + bId);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	/*�亯 �ޱ�*/
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		replyShape(bGroup, bStep);
		
		String query = "insert into mvc_board(bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values(mvc_board_seq.nextval, ?,?,?,?,?,?)";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep)+1);
			pstmt.setInt(6, Integer.parseInt(bIndent)+1);
			int ri = pstmt.executeUpdate();
			
			if(ri==1) {
				System.out.println("reply registrated");
			}else {
				System.err.println("reply unregistrated");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			
		}
	}
	/*���� ���� �������*/
	public BDto getContent(String bId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BDto dto = null;
		String query = "select * from mvc_board where bId = ?";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int bID = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bGroup = rs.getInt("bGroup");
				int bHit = rs.getInt("bHit");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				dto = new BDto(bID, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}else {
				System.out.println("err;BDao.java : there is no data with " + bId);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	/*�� ����*/
	public void modify(String bId, String bName, String bTitle, String bContent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "update mvc_board set bName=?, bTitle=?, bContent=? where bId=?";
		System.out.println(query);
		System.out.println("1" + bId + " 2" + bName+ " 3"+bTitle+ " 4" + bContent);
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bId);
			int ri =pstmt.executeUpdate();
			if(ri==1) {
				System.out.println("update completed");
			}else {
				System.out.println("update failed");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/*�� ����*/
	public void delete(String bId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "delete from mvc_board where bId=?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			int ri =pstmt.executeUpdate();
			
			if(ri==1) {
				System.out.println("delete completed");
			}else {
				System.out.println("delete failed");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	private void replyShape( String strGroup, String strStep) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strGroup));
			preparedStatement.setInt(2, Integer.parseInt(strStep));
			
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	void upHit(String bId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bId);
			
			int rn = preparedStatement.executeUpdate();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}


}
