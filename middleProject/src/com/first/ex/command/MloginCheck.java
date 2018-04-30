package com.first.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.first.com.dao.MDao;

public class MloginCheck implements Mcommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MDao dao = MDao.getInstance();
		int login = dao.loginCheck(id, pw);
		
		/*****************************************************************
		  0 ���� ���� / 1 �α��� ���� / 2 ���̵� ���� / 3 �н����� ����ġ
		 *****************************************************************/
		if(login==1) {
			session.setAttribute("idSession", id);
		}
		request.setAttribute("result", login);
		
	}
	
}
