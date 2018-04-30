package com.first.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.first.com.dao.MDao;

public class MloginCheck implements Mcommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		MDao dao = new MDao();
		int login = dao.loginCheck(id, pw);
		
	}
	
}
