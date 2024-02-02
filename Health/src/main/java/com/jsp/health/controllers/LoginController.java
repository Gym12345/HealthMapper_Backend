package com.jsp.health.controllers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.health.HealthDAO;
import com.jsp.health.UserDAO;
//ULID	NUMBER(5,0)	No
//USERID	NVARCHAR2(20 CHAR)	No
//USERPW	NVARCHAR2(40 CHAR)	No
//USERNAME	NVARCHAR2(40 CHAR)	No
//USERCLASS	NVARCHAR2(40 CHAR)	Yes
//����ó������ �Ϸ�
@WebServlet("/Health1/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO dao;
    
    public void init() {
        // Initialize the DAO
    	dao = UserDAO.getInstance();
    }
    
    
    
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("loginForm.jsp").forward(request, response);
		
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    HttpSession session = request.getSession(); // �̸� ���� ����
//	    
//	    BufferedReader reader = request.getReader();
//	    StringBuilder sb = new StringBuilder();
//	    String line = null;
//	    while ((line = reader.readLine()) != null) {
//	        sb.append(line);
//	    }
//	    
//	    JSONObject json = new JSONObject(sb.toString());
//	    String userId = json.getString("userId");
//	    String userPw = json.getString("userPw");
//	    String userClass = json.getString("userClass");
//
//	    System.out.println(userId+userPw+userClass);
//	    // Check if the entered data is valid
//
//	    int result = dao.userLogin(userId,userPw,userClass);
//
//	    if (result==1) {
//	        // If the login is successful, store the user ID in the session
//	        session.setAttribute("userId", userId);
//	        session.setAttribute("userClass", userClass);
//	        String message = userId+"�� ȯ���մϴ�";
//	        request.setAttribute("message", message);
//	        //request.getRequestDispatcher("serviceTiles.jsp").forward(request, response);
//	        JSONObject responseData = new JSONObject();
//	        responseData.put("success", true); 
//	        responseData.put("message", message);
//	        
//	        // �� �κ��� Ŭ���̾�Ʈ ȯ�濡�� reponse.data���� user�� ���� ������ Ȱ���ϱ� ����.
//	        // ���� ����� ����(��ū)�� �ʿ��ѵ� �� �κе� �߰��� �ʿ��� ����.
//	        responseData.put("userId", userId); 
//	        responseData.put("userPw", userPw);
//	        responseData.put("userClass", userClass);
//	        
//	        response.setContentType("application/json");
//	        response.setCharacterEncoding("UTF-8");
//	        response.getWriter().write(responseData.toString());
//	    } else {
//	        String message = "�α��� ����";
//	        request.setAttribute("message", message);
//	        //request.getRequestDispatcher("loginForm.jsp").forward(request, response);
//	        JSONObject responseData = new JSONObject();
//	        responseData.put("success", false);
//	        responseData.put("message", message);
//	        response.setContentType("application/json");
//	        response.setCharacterEncoding("UTF-8");
//	        response.getWriter().write(responseData.toString());
//	    }
//	}
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the user input from the login form
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userClass = request.getParameter("userClass");
		
		System.out.println(userId+userPw+userClass);
		// Check if the entered data is valid
		
		int result = dao.userLogin(userId,userPw,userClass);
		
		if (result==1) {
		    // If the login is successful, create a session and store the user ID
		    request.getSession().setAttribute("userId", userId);
		    request.getSession().setAttribute("userClass", userClass);
		    String message = userId+"�� ȯ���մϴ�";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("serviceTiles.jsp").forward(request, response);
		} else {
			
			String message = "�α��� ����";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("loginForm.jsp").forward(request, response);
            
		}
	}

}
