package com.java.ex.BCommand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.ex.Dao.BDao;
import com.java.ex.Dto.BDto;
import com.java.ex.Dto.PDto;

public class BListCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BDao dao = BDao.getInstance();
		HttpSession session = request.getSession();
		int curPage=0;
		String tmpCurPage = request.getParameter("curPage");
		
		if(tmpCurPage==null) {
			curPage = (int)session.getAttribute("curPage");
		}else {
			curPage = Integer.parseInt(tmpCurPage);
			session.setAttribute("curPage", curPage);
		}

		
		int totalList = dao.getTotalList();
		
		PDto pdto = new PDto(curPage, totalList);
	
		ArrayList<BDto> dtos = dao.BListCommand(curPage);
		
		request.setAttribute("dtos", dtos);
		request.setAttribute("pdto", pdto);
	}
}
