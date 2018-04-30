package com.middle.ex.MCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.middle.ex.Dao.MDao;

public class MLoginCheckCommand implements MCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MDao dao = MDao.getInstance();
		
	}
	
}
