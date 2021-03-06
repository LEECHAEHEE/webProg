package com.java.ex.BCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.ex.Dao.BDao;

public class BWriteCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name= request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BDao bDao = BDao.getInstance();
		int writeResult = bDao.write(id, name, title, content);
		if(writeResult==1) {
			request.setAttribute("writeResult", writeResult);
		}
	}

}
