package com.java.ex.MCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.ex.Dao.MDao;

public class MLoginCheckCommand implements MCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MDao dao = MDao.getInstance();
		int loginResult = dao.loginCheck(id, pw);
		if(loginResult==1) {
			HttpSession session = request.getSession();
			session.setAttribute("idSession", id);
		}
		request.setAttribute("result", loginResult);
	}
	
}
