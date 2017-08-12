package com.cts.olympicservlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cts.constants.QueryConstants;
import com.cts.exception.OlympicDataException;
import com.cts.service.OlympicService;

public class OlympicAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> approvedList = new ArrayList<String>();
		List<String> disApprovedList = new ArrayList<String>();
		List<String> list = new ArrayList<String>();
		OlympicService olympicService = new OlympicService();

		if (request.getParameterMap().containsKey("approvalValidation")) {

			Map<String, String[]> optionSelected = request.getParameterMap();
			Iterator<String> iterator = optionSelected.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				String[] value = optionSelected.get(key);
				if (value[0].equalsIgnoreCase("approve"))
					approvedList.add(key);
				else if (value[0].equalsIgnoreCase("disapprove"))
					disApprovedList.add(key);
			}

			try {
				olympicService.statusUpdate(approvedList,
						QueryConstants.UPDATE_STATUS);
			} catch (OlympicDataException e) {
				request.setAttribute("error", e.getMessage());
			}
			try {
				olympicService.statusUpdate(disApprovedList,
						QueryConstants.UPDATE_USER_DISPLAY);
			} catch (OlympicDataException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
			}

			try {
				list = olympicService.approveUser();
			} catch (OlympicDataException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
			}
			request.setAttribute("list", list);

			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("AdminApprovalPage.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Map<String, String[]> optionSelected = request.getParameterMap();
			String[] optionSelect = optionSelected.get("radioSelect");
			if (optionSelect[0].equalsIgnoreCase("approveUsers")) {
				try {
					list = olympicService.approveUser();
				} catch (OlympicDataException e) {
					// TODO Auto-generated catch block
					request.setAttribute("error", e.getMessage());
				}
				request.setAttribute("list", list);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("AdminApprovalPage.jsp");
				requestDispatcher.forward(request, response);
			} else {
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("AdminUpload.jsp");
				requestDispatcher.forward(request, response);
			}
		}
	}

}
