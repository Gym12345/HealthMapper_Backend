package com.jsp.health.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.health.HealthDAO;
import com.jsp.health.HealthDTO;


@WebServlet("/Health1/OneHospitalInfoController")
public class OneHospitalInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	 private HealthDAO dao;

		public void init() {
			dao = HealthDAO.getInstance();
	    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String hName=(String)request.getSession().getAttribute("userName");//������������ ��� �α��ν� userName�� �����̸����� ���ű⋚����
	    HealthDTO OneHospitalArr = dao.showMyHospital(hName);
		
		
		
	}

	
	

}
