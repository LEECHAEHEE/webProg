package com.java.ex.FrontController;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.ex.MCommand.MCommand;
import com.java.ex.MCommand.MIdCheck;
import com.java.ex.MCommand.MJoinCommand;
import com.java.ex.MCommand.MLeave;
import com.java.ex.MCommand.MLoginCheckCommand;
import com.java.ex.MCommand.MLogoutCommand;
import com.java.ex.MCommand.MModify;
import com.sun.jmx.snmp.Enumerated;

/**
 * Servlet implementation class MFrontController
 */
@WebServlet({ "/MFrontController", "*.mdo" })
public class MFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		MCommand command = null;
		String viewPage = null;
		
		String uri = request.getRequestURI();
		String ctxtPath = request.getContextPath();
		String com = uri.substring(ctxtPath.length());
		if(com.equals("/loginCheck.mdo")) {
			command = new MLoginCheckCommand();
			command.execute(request, response);
			viewPage="loginResult.jsp";
		}else if(com.equals("/logout.mdo")) {
			command = new MLogoutCommand();
			command.execute(request, response);
			viewPage="login.jsp";
		}else if(com.equals("/join.mdo")) {
			command = new MJoinCommand();
			command.execute(request, response);
			viewPage="joinResult.jsp";
		}else if(com.equals("/idCheck.mdo")) {
			command = new MIdCheck();
			command.execute(request, response);
			viewPage="idCheckPopUp.jsp";
		}else if(com.equals("/modify.mdo")) {
			command = new MModify();
			command.execute(request, response);
			viewPage="modifyResult.jsp";
		}else if(com.equals("/leave.mdo")) {
			command = new MLeave();
			command.execute(request, response);
			viewPage="leaveResult.jsp";
		}
//		HttpSession session = request.getSession();
//		Enumeration<String> se = session.getAttributeNames();
//		while(se.hasMoreElements()) {
//			String getse = se.nextElement()+"";
//			System.out.println("Msession : " + getse + ":" + session.getAttribute(getse));
//		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
