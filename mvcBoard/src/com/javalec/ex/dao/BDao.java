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
import com.javalec.ex.dto.PageDto;


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
	
/*총 게시글 갯수*/
public int CountList() {
	int countlist = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String query = "select count(*) as numlist from mvc_board";
	
	try {
		conn = dataSource.getConnection();
		pstmt = conn.prepareStatement(query);
		rs = pstmt.executeQuery();
		if(rs.next())
			countlist = rs.getInt("numlist");
		
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
	return countlist;
}

/*게시판 목록 보기*/
public ArrayList<BDto> showList(PageDto pdto) {
	ArrayList<BDto> dtos = new ArrayList<>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = "select a.rnum, a.bid, a.bname, a.btitle, a.bcontent, a.bdate, a.bgroup, a.bhit, a.bstep, a.bindent\r\n" + 
			"from ( select rownum as rnum, b.bid, b.bname, b.btitle, b.bcontent, b.bdate, b.bgroup, b.bhit, b.bstep, b.bindent\r\n" + 
			"from ( select bid, bname, btitle, bcontent, bdate, bgroup, bhit, bstep, bindent\r\n" + 
			"        from mvc_board\r\n" + 
			"        order by bgroup desc, bstep asc ) b\r\n" + 
			"where rownum <= ? ) a\r\n" + 
			"where a.rnum >= ?";
	
	try {
		conn = dataSource.getConnection();
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, String.valueOf(pdto.getCurrPage()*10));
		pstmt.setString(2, String.valueOf(pdto.getCurrPage()*10-9));
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
			
			dtos.add(new BDto(bID,bName,bTitle,bContent,bDate,bGroup,bHit,bStep,bIndent ));
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(conn!=null) conn.close();
			if(pstmt != null) pstmt.close();
			if(rs!=null)rs.close();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	return dtos;
}


	/*게시판 작성*/
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
	
	/*게시판 보여주기*/
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
	/*답변 달기*/
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
	/*본문 내용 갖고오기*/
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
	
	/*글 수정*/
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
	
	/*글 삭제*/
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
			
			preparedStatement.executeUpdate();
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
			
			preparedStatement.executeUpdate();
					
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
