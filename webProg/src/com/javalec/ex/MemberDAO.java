package com.javalec.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	public static final int MEMBER_NONEXISTENT=0;
	public static final int MEMBER_EXISTENT=1;
	public static final int MEMBER_JOIN_FAIL=0;
	public static final int MEMBER_JOIN_SUCCESS=1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD=0;
	public static final int MEMBER_LOGIN_SUCCESS=1;
	public static final int MEMBER_LOGIN_IS_NOT=-1;
	
	/*singleton design make object only one and shared it in all*/
	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {
		// TODO Auto-generated constructor stub
	}
	
	/*make static method to get MemberDAO object*/
	public static MemberDAO getInstance() {
		return instance;
	}
	
	/*회원정보 삽입*/
	public int insertMember(MemberDTO dto) {
		int ri=0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into members values(?,?,?,?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPw());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getAddress());
			pstmt.setTimestamp(6, dto.getrDate());
			pstmt.executeUpdate();
			ri = MemberDAO.MEMBER_JOIN_SUCCESS;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
		}
		return ri;
	}
	
	/*아이디 중복 검사*/
	public int confirmId(String id) {
		int ri=0;
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select id from members where id = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "id");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				ri = MemberDAO.MEMBER_EXISTENT;
			}else {
				ri = MemberDAO.MEMBER_NONEXISTENT;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	/*로그인 유효성 검사*/
	public int userCheck(String id, String pw) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from members where id=?";
		try {
			conn = getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dbPw = rs.getString("pw");
				
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("pw"));
				
				if(dbPw.equals(pw)) {
					ri = MemberDAO.MEMBER_LOGIN_SUCCESS; //로그인 성공
				}else {
					ri = MemberDAO.MEMBER_LOGIN_PW_NO_GOOD; //패스워드 틀림
				}
			}else {
				ri = MemberDAO.MEMBER_LOGIN_IS_NOT; //해당 아이디 없음
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
		return ri;
	}
	/*회원정보 읽어들이기*/
	public MemberDTO getMember(String id) {
		MemberDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from members where id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));
				dto.setrDate(rs.getTimestamp("rDate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn !=null) conn.close();
				if(pstmt !=null) pstmt.close();
				if(rs !=null) rs.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public int updateMember(MemberDTO dto) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "update members set name=?, pw=?, email=?, address=? where id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getId());
			ri = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}

	private Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			connection = dataSource.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
