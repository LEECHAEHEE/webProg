package com.java.ex.MCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.ex.Dao.MDao;

public class MModify implements MCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		MDao dao = MDao.getInstance();
		int modifyResult = dao.modify(id, name, pw, email, address);
		if(modifyResult==1) {
			HttpSession session = request.getSession();
			session.setAttribute("nameSession", name);
			request.setAttribute("modifyResult", modifyResult);
		}
	}
	
}
