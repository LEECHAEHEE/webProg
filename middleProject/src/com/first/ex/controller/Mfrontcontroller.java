package com.first.ex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.smartcardio.CommandAPDU;

import com.first.ex.command.Mcommand;
import com.first.ex.command.MloginCheck;

@WebServlet({ "/frontcontroller", "*.mdo" })
public class Mfrontcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Mfrontcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR") ;
		/*******************************************************************************
		 * frontcontroller 패턴을 이용한다. 확장자패턴(*.mdo) 클라이언트의 다양한요청을 
		 * 한곳으로 집중시킨다. 또한 Command 패턴을 이용한다. Command 패턴은
		 * 클라이언트로 부터 받은 요청들에 대해서 서블릿이 직접 작업을하지 않고 관련
		 * 클래스가 처리하도록 하는 디자인패턴이다. 
		 * 모든 servlet 혹은 jsp 에서의 요청을 받는다. forwarding을 통해 해당 요청을
		 * 처리하는 컴포넌트로 위임한다. 
		 */
		Mcommand command = null;
		
		String ctxPath = request.getContextPath();
		String reqUri = request.getRequestURI();
		String pattern = reqUri.substring(ctxPath.length());
		
		String viewPage = null;
		
	
		if(pattern.equals("/login.mdo")) {
			viewPage = "login.jsp";
		}else if(pattern.equals("/login_check.mdo")) {
			command = new MloginCheck();
			command.execute(request, response);
			viewPage = "login_check.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
