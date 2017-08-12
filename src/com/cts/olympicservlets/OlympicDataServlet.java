package com.cts.olympicservlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cts.file.FileParse;
import com.cts.olympicpojo.OlympicDataPojo;
import com.cts.service.OlympicDataService;


public class OlympicDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<OlympicDataPojo> fileReading(String file) throws IOException {
		File olympicGamesCSV = new File(file);
		BufferedReader datareading = new BufferedReader(new FileReader(
				olympicGamesCSV));
		datareading.readLine();
		List<OlympicDataPojo> parsedData = new ArrayList<OlympicDataPojo>();
		 FileParse fileParse = new FileParse();
		parsedData = fileParse.parseData(datareading);
		return parsedData;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String file = request.getParameter("file");
		List<OlympicDataPojo>parsedData=new ArrayList<OlympicDataPojo>();
		parsedData=fileReading(file);
		OlympicDataService olympicService=new OlympicDataService();
		try {
			olympicService.loadHost(parsedData);
			olympicService.loadDiscipline(parsedData);
			olympicService.loadAthlete(parsedData);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("AdminWelcomePage.jsp");
			requestDispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
