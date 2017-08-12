package com.cts.olympicservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cts.constants.ExceptionConstants;
import com.cts.exception.OlympicDataException;
import com.cts.service.OlympicService;

/**
 * Servlet implementation class OlympicUserLoginServlet
 */
public class OlympicUserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " " + password);
		session.setAttribute("userName",username);
		OlympicService olympicService = new OlympicService();
		if (username.equals("") && password.equals("")) {
			request.setAttribute("error", ExceptionConstants.EMPTY_FIELDS);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("Login.jsp");
			requestDispatcher.forward(request, response);
		} else {
			if (username.equals("") || password.equals("")) {
				request.setAttribute("error",
						ExceptionConstants.INCORRECT_CREDENTIALS);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("Login.jsp");
				requestDispatcher.forward(request, response);
			} else {
				try {
					if (olympicService.validate(username, password)) {
						System.out.println(username + " hey " + password);
						if (olympicService.validateUsers(username, password)) {
							RequestDispatcher requestDispatcher1 = request
									.getRequestDispatcher("UserLogin.jsp");
							requestDispatcher1.forward(request, response);
						} else if (olympicService.validateAdmin(username,
								password)) {
							RequestDispatcher requestDispatcher = request
									.getRequestDispatcher("AdminWelcomePage.jsp");
							requestDispatcher.forward(request, response);
						}

					} else {
						request.setAttribute("error",
								ExceptionConstants.USER_NOT_APPROVED);
						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("Login.jsp");
						requestDispatcher.forward(request, response);
					}
				} catch (OlympicDataException e) {
					// TODO Auto-generated catch block
					request.setAttribute("error", e.getMessage());
				}
			}
		}

	}

}
