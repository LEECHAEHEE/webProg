package com.javalec.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.dao.BDao;
import com.javalec.ex.dto.BDto;

public class BContentCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int bId= Integer.parseInt(request.getParameter("bId"));
		
		
		BDao dao = new BDao();
		BDto dto = dao.showContent(bId);
		
		request.setAttribute("content", dto);
	}

}
