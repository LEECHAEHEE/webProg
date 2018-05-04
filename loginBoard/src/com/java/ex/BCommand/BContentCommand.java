package com.java.ex.BCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ex.Dao.BDao;
import com.java.ex.Dto.BDto;

public class BContentCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");
		String curPage= request.getParameter("curPage");
		
		BDao bDao = BDao.getInstance();
		BDto bDto =	bDao.content(num);
		if(bDto!=null) {
			request.setAttribute("bDto", bDto);
			request.setAttribute("curPage", curPage);
		}
		
	}

}
