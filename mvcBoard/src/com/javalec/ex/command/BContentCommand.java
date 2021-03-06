package com.javalec.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.dao.BDao;
import com.javalec.ex.dto.BDto;

public class BContentCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bId= request.getParameter("bId");
		String currPage = request.getParameter("currpage");
				
		BDao dao = new BDao();
		BDto dto = dao.showContent(bId);
		
		request.setAttribute("content", dto);
		request.setAttribute("currpage", currPage);
	}

}
