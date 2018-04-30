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
		 * frontcontroller ������ �̿��Ѵ�. Ȯ��������(*.mdo) Ŭ���̾�Ʈ�� �پ��ѿ�û�� 
		 * �Ѱ����� ���߽�Ų��. ���� Command ������ �̿��Ѵ�. Command ������
		 * Ŭ���̾�Ʈ�� ���� ���� ��û�鿡 ���ؼ� ������ ���� �۾������� �ʰ� ����
		 * Ŭ������ ó���ϵ��� �ϴ� �����������̴�. 
		 * ��� servlet Ȥ�� jsp ������ ��û�� �޴´�. forwarding�� ���� �ش� ��û��
		 * ó���ϴ� ������Ʈ�� �����Ѵ�. 
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
