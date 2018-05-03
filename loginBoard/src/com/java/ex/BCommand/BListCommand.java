package com.java.ex.BCommand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ex.Dao.BDao;
import com.java.ex.Dto.BDto;
import com.java.ex.Dto.PDto;

public class BListCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BDao dao = BDao.getInstance();
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int totalList = dao.getTotalList();
		System.out.println("curPage : " + curPage);
		
		PDto pdto = new PDto(curPage, totalList);
	
		System.out.println("getCurPage() : " + pdto.getCurPage());
		System.out.println("getStartPage() : " + pdto.getStartPage());
		System.out.println("getLastPage() : " + pdto.getLastPage());
		System.out.println("getTotalList() : " + pdto.getTotalList());
		System.out.println("getTotalPage() : " + pdto.getTotalPage());
		
		ArrayList<BDto> dtos = dao.BListCommand(curPage);
		
		//System.out.println("BListCommand 17 :" + dtos.get(0).getName() + " " + dtos.get(0).getrDate());
		request.setAttribute("dtos", dtos);
		request.setAttribute("pdto", pdto);
	}

}
