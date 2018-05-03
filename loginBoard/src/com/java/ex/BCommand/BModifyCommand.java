package com.java.ex.BCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ex.Dao.BDao;

public class BModifyCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BDao bDao = BDao.getInstance();
		int modifyResult = bDao.modify(num, title, content);
		if(modifyResult==1) {
			request.setAttribute("modifyResult", modifyResult);
			request.setAttribute("num", num);
		}
		
	}

}
