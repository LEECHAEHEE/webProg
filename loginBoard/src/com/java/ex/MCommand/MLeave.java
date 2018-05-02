package com.java.ex.MCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.ex.Dao.MDao;

public class MLeave implements MCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("idSession");
		System.out.println("id : " + id);
		MDao dao = MDao.getInstance();
		int leaveResult = dao.leave(id);
		if(leaveResult ==1) {
			request.setAttribute("leaveResult", leaveResult);
		}
	}

}
