package com.java.ex.MCommand;

import java.util.ArrayList;

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
		ArrayList<Object> result = dao.loginCheck(id, pw);
		if((int)result.get(0)==1) {
			HttpSession session = request.getSession();
			session.setAttribute("idSession", id);
			session.setAttribute("nameSession", (String)result.get(1));
		}
		
		request.setAttribute("result", result.get(0));
	}
	
}
