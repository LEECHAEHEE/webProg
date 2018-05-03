package com.java.ex.BCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ex.Dao.BDao;

public class BDeleteCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");
		BDao bDao = BDao.getInstance();
		int deleteResult = bDao.delete(num);
		if(deleteResult ==1) {
			request.setAttribute("deleteResult", deleteResult);
		}
	}

}
