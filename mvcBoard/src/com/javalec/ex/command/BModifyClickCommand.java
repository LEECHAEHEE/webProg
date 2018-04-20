package com.javalec.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.dao.BDao;

public class BModifyClickCommand implements BCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bId= request.getParameter("bId");
		String bName= request.getParameter("bName");
		String bTitle= request.getParameter("bTitle");
		String bContent= request.getParameter("bContent");
		
		System.out.println("BModifyClickCommand: " + bId + " " + bName + " " + bTitle + " " + bContent );

		BDao dao = new BDao();
		dao.modify( bId,  bName,  bTitle,  bContent);
				
	}

}
