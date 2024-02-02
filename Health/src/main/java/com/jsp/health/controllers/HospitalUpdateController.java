package com.jsp.health.controllers;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.jsp.health.HealthDAO;
import com.jsp.health.HealthDTO;

//�̿�
@WebServlet("/Health1/HospitalUpdateController")
public class HospitalUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private HealthDAO dao;
	
	public void init() throws ServletException {
		dao = HealthDAO.getInstance();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("hospitalUpdateForm.jsp").forward(request, response);//���⼭ getParameter �� parts.jsp ���� �Ѱܹ����� �ȹ޾Ƶ�,
		//hospitalUpdateForm ���� �Ѿ��
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String currentPart=(String) request.getSession().getAttribute("CurrentPart");
		request.getSession().setAttribute("CurrentPart", currentPart);
		
		String currentDepartment=(String) request.getSession().getAttribute("CurrentDepartment");
		request.getSession().setAttribute("CurrentDepartment", currentDepartment);
		
		String id=request.getParameter("hId");
		int hId=Integer.parseInt(id);
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
        	String message = "�ٲ��̹������� �Է����ּ���";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("hospitalUpdateForm.jsp").forward(request, response);
            
        }
        else if(hName.equals("")) {
        	String message = "�ٲ� �̸��� �Է����ּ���";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("hospitalUpdateForm.jsp").forward(request, response);
            
        }
        else if(hAddress.equals("")) {
        	String message = "�ٲ� �ּҸ� �Է����ּ���";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("hospitalUpdateForm.jsp").forward(request, response);
            
        }
        else if(hPart.equals("")) {
        	String message = "�ٲ� ������ �������ּ���";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("hospitalUpdateForm.jsp").forward(request, response);
            
        }else if(hDepartment.equals("")) {
        	String message = "����� �Է����ּ���";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("hospitalUpdateForm.jsp").forward(request, response);
            
        }else if(hDescription.equals("")) {
        	String message = "�������� �Է����ּ���";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("hospitalUpdateForm.jsp").forward(request, response);
            
        }else if(hDomain.equals("")) {
        	String message = "��������Ʈ�ּҸ� �Է����ּ���";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("hospitalUpdateForm.jsp").forward(request, response);
            
        }else if(hLongitude.equals("")) {
        	String message = "�浵 �Է����ּ���";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("hospitalUpdateForm.jsp").forward(request, response);
            
        }else if(hLatitude.equals("")) {
        	String message = "���� �Է����ּ���";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("hospitalUpdateForm.jsp").forward(request, response);
            
        }
        else {
        
        
        
        	
	        HealthDTO healthDTO = new HealthDTO();
	        healthDTO.sethId(hId);
	        healthDTO.sethPart(hPart);
	        healthDTO.sethImg(hImg);
	        healthDTO.sethName(hName);
	        healthDTO.sethAddress(hAddress);
	        healthDTO.sethDepartment(hDepartment);
	        healthDTO.sethDescription(hDescription);
	        healthDTO.sethDomain(hDomain);
	        healthDTO.sethLongitude(hLongitude);
	        healthDTO.sethLatitude(hLatitude);
	        String encodedhPart;
	        if (currentPart == null) {
	          encodedhPart = "";
	        } else {
	          encodedhPart = URLEncoder.encode(currentPart, "UTF-8");
	        }

	        String encodedhDepartment;
	        if (currentDepartment == null) {
	          encodedhDepartment = "";
	        } else {
	          encodedhDepartment = URLEncoder.encode(currentDepartment, "UTF-8");
	        }
	        
	       

	        int result = dao.HospitalUpdate(healthDTO);
	        String prevUrl=(String) request.getSession().getAttribute("prevUrl");
	        if (result == 1 && prevUrl.contains("BodyPartsController")) {
	        	
	        	String message = "������������";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("parts.jsp?part="+encodedhPart).forward(request, response);
	        	
	        }else if(result == 1 && prevUrl.contains("MedicalDepartmentListController")){
	        	
	        	String message = "������������";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("medicalDepartmentList.jsp?department="+encodedhDepartment).forward(request, response);
	        	
	        }		
	        	else  if (result == 0 && prevUrl.contains("BodyPartsController")){
	        	String message = "������������";
	        	request.setAttribute("message", message);
	            request.getRequestDispatcher("parts.jsp?part="+encodedhPart).forward(request, response);
	        }
	        	else  if (result == 0 && prevUrl.contains("MedicalDepartmentListController")){
		        	String message = "������������";
		        	request.setAttribute("message", message);
		            request.getRequestDispatcher("medicalDepartmentList.jsp?department="+encodedhDepartment).forward(request, response);
		        }
		}
	}

}
