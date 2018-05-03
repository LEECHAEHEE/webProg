package com.java.ex.BCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.ex.Dao.BDao;
import com.java.ex.Dto.BDto;

public class BContentCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");
		BDao bDao = BDao.getInstance();
		BDto bDto =	bDao.content(num);
		if(bDto!=null) {
			request.setAttribute("bDto", bDto);
		}
		
	}

}
