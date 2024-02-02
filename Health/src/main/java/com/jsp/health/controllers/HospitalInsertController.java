package com.jsp.health.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.health.HealthDAO;
import com.jsp.health.HealthDTO;

//����ó������ �Ϸ� 
@WebServlet("/Health1/HospitalInsertController")
public class HospitalInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	 private HealthDAO dao;
	    
	    public void init() {
	        // Initialize the DAO
	    	dao = HealthDAO.getInstance();
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Display the insert form
	        request.getRequestDispatcher("hospitalInsertForm.jsp").forward(request, response);
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Handle form submission
	    	request.setCharacterEncoding("UTF-8");
	        String hImg = request.getParameter("hImg");
	        String hName = request.getParameter("hName");
	        String hAddress = request.getParameter("hAddress");
	        String hPart = request.getParameter("hPart");
	        String hDepartment = request.getParameter("hDepartment");
	        String hDescription = request.getParameter("hDescription");
	        String hDomain = request.getParameter("hDomain");
	        String hLongitude=request.getParameter("hLongitude");
	        String hLatitude=request.getParameter("hLatitude");

	        if(hImg.equals("")) {
	        	String message = "�̹����� �������ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("hospitalInsertForm.jsp").forward(request, response);
	            
	        }
	        else if(hName.equals("")) {
	        	String message = "�̸��� �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("hospitalInsertForm.jsp").forward(request, response);
	            
	        }
	        else if(hAddress.equals("")) {
	        	String message = "�ּҸ� �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("hospitalInsertForm.jsp").forward(request, response);
	            
	        }
	        else if(hPart.equals("")) {
	        	String message = "������ �������ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("hospitalInsertForm.jsp").forward(request, response);
	            
	        }else if(hDepartment.equals("")) {
	        	String message = "����� �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("hospitalInsertForm.jsp").forward(request, response);
	            
	        }else if(hDescription.equals("")) {
	        	String message = "�������� �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("hospitalInsertForm.jsp").forward(request, response);
	            
	        }else if(hDomain.equals("")) {
	        	String message = "��������Ʈ�ּҸ� �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("hospitalInsertForm.jsp").forward(request, response);
	            
	        }else if(hLongitude.equals("")) {
	        	String message = "�浵 �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("hospitalInsertForm.jsp").forward(request, response);
	            
	        }else if(hLatitude.equals("")) {
	        	String message = "���� �Է����ּ���";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("hospitalInsertForm.jsp").forward(request, response);
	            
	        }
	        
	        else {
	        	HealthDTO healthDTO = new HealthDTO();
		        healthDTO.sethImg(hImg);
		        healthDTO.sethName(hName);
		        healthDTO.sethAddress(hAddress);
		        healthDTO.sethPart(hPart);
		        healthDTO.sethDepartment(hDepartment);
		        healthDTO.sethDescription(hDescription);
		        healthDTO.sethDomain(hDomain);
		        healthDTO.sethLongitude(hLongitude);
		        healthDTO.sethLatitude(hLatitude);
		        int result = dao.HospitalCreate(healthDTO);
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

