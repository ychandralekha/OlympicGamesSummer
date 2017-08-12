package com.cts.olympicservlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cts.exception.OlympicDataException;
import com.cts.service.OlympicService;

public class OlympicUserRegisterationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]>keyValueSet=request.getParameterMap();
		OlympicService olympicService=new OlympicService();	
		try {
				if(olympicService.loadUserField(keyValueSet))
				{
					RequestDispatcher requestDispatcher=request.getRequestDispatcher("Login.jsp");
					requestDispatcher.forward(request, response);
				}
			} catch (OlympicDataException e) {
				request.setAttribute("error",e.getMessage());
			}
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("RegisterPage.jsp");
			requestDispatcher.forward(request, response);
			
		
	}

}
