package com.jsp.health.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.health.RequestDAO;
import com.jsp.health.RequestDTO;


@WebServlet("/Health1/HosOwnersRequestCheckController")
public class HosOwnersRequestCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private RequestDAO dao;
	    
	    public void init() {
	        // Initialize the DAO
	    	dao = RequestDAO.getInstance();
	    }
    

	


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("reqId");
		int reqId=Integer.parseInt(id);		
		int result1=dao.RequestInsertToHospital(dao.RequestOne(reqId));
		int result2=dao.Requestdelete(reqId);//���⼭ (����Session�� ����� RequestArr�� delete �� )RequestArr �̹ٲ���� ������ �ٲ� �ɷ� �ٽ� session ��set �������
		
		ArrayList<RequestDTO> RequestArr = dao.RequestList();
	    request.getSession().setAttribute("RequestArr", RequestArr);
		if(result1==1 && result2==1) {
			String message = "������Ͽ�ûó�� ����";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("HosOwnersRequestList.jsp").forward(request, response);
		}
		else {
        	String message = "������Ͽ�ûó�� ����";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("HosOwnersRequestList.jsp").forward(request, response);
        }
		
	}

}
