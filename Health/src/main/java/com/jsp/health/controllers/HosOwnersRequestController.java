package com.jsp.health.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.jsp.health.RequestDAO;
import com.jsp.health.RequestDTO;


@WebServlet("/Health1/HosOwnersRequestController")
public class HosOwnersRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private RequestDAO dao;
    
    public void init() {
        // Initialize the DAO
    	dao = RequestDAO.getInstance();
    }

	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Display the insert form
	        request.getRequestDispatcher("HosOwnersRequestForm.jsp").forward(request, response);
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Handle form submission
	    	request.setCharacterEncoding("UTF-8");
	        String reqImg = request.getParameter("reqImg");
	        String reqName = request.getParameter("reqName");
	        String reqPart = request.getParameter("reqPart");

	        String reqAddress = request.getParameter("reqAddress");
	        String reqDepartment = request.getParameter("reqDepartment");
	        String reqDescription = request.getParameter("reqDescription");
	        String reqDomain = request.getParameter("reqDomain");
	        String reqLongitude=request.getParameter("reqLongitude");
	        String reqLatitude=request.getParameter("reqLatitude");

	        if(reqImg.equals("")) {
	        	String message = "�̹����� �������ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("HosOwnersRequestForm.jsp").forward(request, response);
	            
	        }
	        else if(reqName.equals("")) {
	        	String message = "�̸��� �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("HosOwnersRequestForm.jsp").forward(request, response);
	            
	        }
	        else if(reqAddress.equals("")) {
	        	String message = "�ּҸ� �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("HosOwnersRequestForm.jsp").forward(request, response);
	            
	        }
	        else if(reqPart.equals("")) {
	        	String message = "������ �������ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("HosOwnersRequestForm.jsp").forward(request, response);
	            
	        }else if(reqDepartment.equals("")) {
	        	String message = "����� �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("HosOwnersRequestForm.jsp").forward(request, response);
	            
	        }else if(reqDescription.equals("")) {
	        	String message = "�������� �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("HosOwnersRequestForm.jsp").forward(request, response);
	            
	        }else if(reqDomain.equals("")) {
	        	String message = "��������Ʈ�ּҸ� �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("HosOwnersRequestForm.jsp").forward(request, response);
	            
	        }
	        else if(reqLongitude.equals("")) {
	        	String message = "�浵 �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("HosOwnersRequestForm.jsp").forward(request, response);
	            
	        }else if(reqLatitude.equals("")) {
	        	String message = "���� �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("HosOwnersRequestForm.jsp").forward(request, response);
	            
	        }
	        else {
	        	RequestDTO requestDTO = new RequestDTO();
	        	requestDTO.setReqImg(reqImg);
	        	requestDTO.setReqName(reqName);
	        	requestDTO.setReqAddress(reqAddress);
	        	requestDTO.setReqPart(reqPart);
	        	requestDTO.setReqDepartMent(reqDepartment);
	        	requestDTO.setReqDescription(reqDescription);
	        	requestDTO.setReqDomain(reqDomain);
		        requestDTO.setReqLongitude(reqLongitude);
		        requestDTO.setReqLatitude(reqLatitude);
		        int result = dao.RequestCreate(requestDTO);
		        
		        String prevUrl=(String) request.getSession().getAttribute("prevUrl");
		        
		        if (result == 1 && prevUrl.contains("BodyPartsController")) {
		        	
		        	String message = "������ϼ���";
		        	request.setAttribute("message", message);
		            request.getRequestDispatcher("bodyparts.jsp").forward(request, response);
		        	
		        	
		        }else if(result == 1 && prevUrl.contains("MedicalDepartmentListController")){
		        	
		        	String message = "������ϼ���";
		        	request.setAttribute("message", message);
		            request.getRequestDispatcher("medicalDepartment.jsp").forward(request, response);
		            		
		        	
		        }		
		        	else  if (result == 0 && prevUrl.contains("BodyPartsController")){
		        		String message = "������Ͻ���";
			        	request.setAttribute("message", message);
			            request.getRequestDispatcher("bodyparts.jsp").forward(request, response);
		        }
		        	else  if (result == 0 && prevUrl.contains("MedicalDepartmentListController")){
			        	String message = "������Ͻ���";
			        	request.setAttribute("message", message);
			            request.getRequestDispatcher("medicalDepartment.jsp").forward(request, response);
			        }
		        
	        }
	        
	    
	}

}
