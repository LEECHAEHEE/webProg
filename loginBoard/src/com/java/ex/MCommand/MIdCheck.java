package com.java.ex.MCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ex.Dao.MDao;

public class MIdCheck implements MCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		MDao dao = MDao.getInstance();
		int idResult= dao.idCheck(id);
		request.setAttribute("idResult", idResult);
	}

}
