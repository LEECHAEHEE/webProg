package com.middle.ex.MFrontController;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.middle.ex.MCommand.MCommand;
import com.middle.ex.MCommand.MLoginCheckCommand;

/**
 * Servlet implementation class MFrontController
 */
@WebServlet("*.mdo")
public class MFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR") ;

		String viewpage = null;
		MCommand command = null;
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String com = uri.substring(contextPath.length());
		
		System.out.println(com);
		
		if(com.equals("/loginCheck.mdo")) {
			command = new MLoginCheckCommand();
			command.execute(request, response);
			viewpage="login/loginCheck.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
		dispatcher.forward(request, response);
	}

}
