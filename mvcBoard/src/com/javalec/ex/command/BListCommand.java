package com.javalec.ex.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.dao.BDao;
import com.javalec.ex.dto.BDto;
import com.javalec.ex.dto.PageDto;

public class BListCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<BDto> dtos = new ArrayList<>();	//게시글 정보 담을 dto arraylist

		String page = (String)request.getParameter("page"); //jsp 에서 현재 page를 받아온다.
		//String contentNum = (String)request.getParameter("contentNum");
		
		int ipage = Integer.parseInt(page);
		//int icontentNum = Integer.parseInt(contentNum);
		
		BDao bDao = new BDao();
		PageDto pdto = new PageDto(); 				//페이징 정보 담을 dto
	
		
		/****************************
		 * 페이지 객체에 정보 담기  *
		 ****************************/
		pdto.setTotalCount(bDao.CountList());
		pdto.setCurrPage(ipage);
		//pdto.setContentNum(icontentNum);
		pdto.setCurrBlock(ipage);
		pdto.setEndBlock(pdto.getTotalCount());
		pdto.prevnext(ipage);
		pdto.setStartpage(pdto.getCurrBlock());
		pdto.setEndPage(pdto.getEndBlock(), pdto.getCurrBlock());
	
		dtos = bDao.showList(pdto);		//리스트 받아오기, 페이징 정보 같이 넘겨줌
		request.setAttribute("list", dtos);
		request.setAttribute("page", pdto);
	}

}
