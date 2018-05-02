package com.java.ex.FrontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ex.BCommand.BCommand;
import com.java.ex.BCommand.BListCommand;
import com.java.ex.BCommand.BWriteCommand;
import com.java.ex.MCommand.MCommand;

@WebServlet("*.bdo")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BFrontController() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		BCommand command = null;
		
		String uri = request.getRequestURI();
		String ctxtPath = request.getContextPath();
		String com = uri.substring(ctxtPath.length());
		
		if(com.equals("/list.bdo")) {
			command = new BListCommand();
			command.execute(request, response);
			viewPage="list.jsp";
		}else if(com.equals("/write.bdo")) {
			command = new BWriteCommand();
			command.execute(request, response);
			viewPage="writeResult.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
