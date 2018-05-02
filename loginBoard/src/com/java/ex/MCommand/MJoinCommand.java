package com.java.ex.MCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.ex.Dao.MDao;
import com.java.ex.Dto.MDto;

public class MJoinCommand implements MCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		MDto dto = new MDto(name, id , pw, email, address);
		MDao dao = MDao.getInstance();
		
		int joinResult = dao.join(dto);
		if(joinResult == 1) { 
			HttpSession session = request.getSession();
			session.setAttribute("idSession", id);
			session.setAttribute("nameSession", name);
		}
	}	
}
