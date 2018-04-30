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
import com.javalec.ex.dto.PagingDTO;

public class PagingDAO {
	
	Context context = null;
	DataSource dataSource = null;
	
	public PagingDAO() {
		// TODO Auto-generated constructor stub
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public PagingDTO getPaging(int page) {
		int totalCount = 0;
		PagingDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select count(*) as totalCount from mvc_board";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("totalCount");
			}
			
			int totalPage = totalCount/10;
			if(totalCount % 10 > 0) totalPage++;
			
			int startPage = ((page-1)/10 * 10 +1);
			int endPage = startPage + 10 -1;
			if(endPage > totalPage) endPage = totalPage;

			int startCount = (page-1)*10+1;
			int endCount = page * 10;
			
			dto = new PagingDTO(totalCount, startPage, endPage, startCount, endCount);
			
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
}
