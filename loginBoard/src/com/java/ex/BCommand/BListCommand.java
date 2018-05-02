package com.java.ex.BCommand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ex.Dao.BDao;
import com.java.ex.Dto.BDto;

public class BListCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BDao dao = BDao.getInstance();
		ArrayList<BDto> dtos = dao.BListCommand();
		request.setAttribute("dtos",dtos);
	}

}
