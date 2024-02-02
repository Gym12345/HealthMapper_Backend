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
import com.jsp.health.ReviewDAO;
import com.jsp.health.ReviewDTO;

//�̿�
@WebServlet("/Health1/HospitalReviewUpdateController")
public class HospitalReviewUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private ReviewDAO dao;
	
	public void init() throws ServletException {
		dao = ReviewDAO.getInstance();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("hospitalReviewUpdateForm.jsp").forward(request, response);//���⼭ getParameter �� parts.jsp ���� �Ѱܹ����� �ȹ޾Ƶ�,
		//hospitalUpdateForm ���� �Ѿ��
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id=request.getParameter("hrId");
		int hrId=Integer.parseInt(id);
		
        String hName = request.getParameter("hName");
        String userId = request.getParameter("userId");
        String hrComment = request.getParameter("hrComment");
        
        String rate = request.getParameter("hrRate");
        int hrRate=Integer.parseInt(rate);
        
        if(hName.equals("")) {
        	String message = "�ٲ� ������ �Է����ּ���";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("hospitalReviewUpdateForm.jsp").forward(request, response);
            
        }
        else if(userId.equals("")) {
        	String message = "�ٲ� �̸��� �Է����ּ���";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("hospitalReviewUpdateForm.jsp").forward(request, response);
            
        }
        else if(hrComment.equals("")) {
        	String message = "�ٲ� �ڸ�Ʈ�� �Է����ּ���";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("hospitalReviewUpdateForm.jsp").forward(request, response);
            
        }else if(hrRate==0) {
        	String message = "�ٲ� ������ �Է����ּ���";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("hospitalReviewUpdateForm.jsp").forward(request, response);
            
        }
       
        else {
        
        
        
        
	        ReviewDTO reviewDTO = new ReviewDTO();
	        reviewDTO.setHrId(hrId);
	        reviewDTO.sethName(hName);
	        reviewDTO.setUserId(userId);
	        reviewDTO.setHrComment(hrComment);
	        reviewDTO.setHrRate(hrRate);
	        String encodedhName = URLEncoder.encode(hName, "UTF-8") ;
	        int result=dao.ReviewUpdate(reviewDTO);
	        if (result == 1) {
	        	System.out.println("ReviewUpdate successed");
	            response.sendRedirect("hospitalReviewList.jsp?hName=" + encodedhName);
	        } else {
	        	System.out.println("ReviewUpdate failed");
	            response.sendRedirect("hospitalReviewList.jsp?hName=" + encodedhName);
	        }
	        
	        
        }
	}

}
