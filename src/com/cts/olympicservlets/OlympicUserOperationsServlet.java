package com.cts.olympicservlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cts.olympicpojo.OlympicDataPojo;
import com.cts.service.OlympicDataService;
import com.cts.util.UpdatePageUtil;

public class OlympicUserOperationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    		throws ServletException, IOException {

	    Map<String,String[]> parameterMap=req.getParameterMap();	
	    OlympicDataService olympicService=new OlympicDataService();
	    Map<String, Map<String, List<String>>>sportDisciplineEvent=new HashMap<>();
	    Map<Integer, String>hostValues=new LinkedHashMap<Integer, String>();
	    Set<String>country=new HashSet<>();
	    
		 if(parameterMap.containsKey("sport1"))
		 {
			 System.out.println("entered to get disp");
			 String sport=parameterMap.get("sport1")[0];
			 
			 req.setAttribute("sport", sport);
		
			 try{
					hostValues=olympicService.selectHost();
					sportDisciplineEvent=olympicService.selectDiscipline();
				 
				 Map<String, List<String>>inner=sportDisciplineEvent.get(sport);
				 
				 Set<String>discList=inner.keySet();
				 
				 req.setAttribute("yearList", hostValues);
				req.setAttribute("sportList", sportDisciplineEvent);
				req.setAttribute("disciplineList",discList);
				
				country=olympicService.selectCountry();
				req.setAttribute("countryList", country);
			 }catch(SQLException e)
			 {
			 }
			 if(parameterMap.containsKey("addpage"))
			 {
			 RequestDispatcher dispatcher=req.getRequestDispatcher("AddingPage.jsp");
			 dispatcher.forward(req, resp);
			 }
			 else if(parameterMap.containsKey("updatepage"))
			 {
				 RequestDispatcher dispatcher=req.getRequestDispatcher("UpdatePage.jsp");
				 dispatcher.forward(req, resp);
			 }
		 }
		 if(parameterMap.containsKey("actionevent"))
		 {
			 String sport=parameterMap.get("sport")[0];
			 String disp=parameterMap.get("discipline")[0];
			 
			 req.setAttribute("sport", sport);
			 req.setAttribute("discipline", disp);
			 
			 List<String>eventList=new ArrayList<String>();
			 try{
				 sportDisciplineEvent=olympicService.selectDiscipline();
				 hostValues=olympicService.selectHost();
				 Map<String,List<String>>inner=sportDisciplineEvent.get(sport);
				 Set<String>discList=inner.keySet();
				 Iterator<String>it=inner.keySet().iterator();
				 while(it.hasNext())
				 {
					 String dis=it.next();
					 List<String>value=new ArrayList<>();
					 value=inner.get(dis);
					 if(dis.equalsIgnoreCase(disp))
					 {
					 eventList.addAll(value);
					 }
				 }
				req.setAttribute("yearList", hostValues);
				req.setAttribute("sportList", sportDisciplineEvent);
				req.setAttribute("disciplineList",discList);
				req.setAttribute("eventList", eventList);
				country=olympicService.selectCountry();
				req.setAttribute("countryList", country);
			 }catch(SQLException e)
			 {
			 }
			 if(parameterMap.containsKey("addpage"))
			 {
			 RequestDispatcher dispatcher=req.getRequestDispatcher("AddingPage.jsp");
			 dispatcher.forward(req, resp);
			 }
			 else if(parameterMap.containsKey("updatepage"))
			 {
				 RequestDispatcher dispatcher=req.getRequestDispatcher("UpdatePage.jsp");
				 dispatcher.forward(req, resp);
			 }
		 }
		 
	 }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String[]>options=request.getParameterMap();
		if(options.containsKey("addPage"))
		{
			String sport=request.getParameter("sportselect");
			String discipline=request.getParameter("disciplineselect");
			String event=request.getParameter("eventselect");
			String year=request.getParameter("yearselect");
			String athlete=request.getParameter("athlete");
			String country=request.getParameter("countryselect");
			String gender=request.getParameter("genderselect");
			String medal=request.getParameter("medalselect");
			String yearCity[]=year.split("=");
			String record=yearCity[0]+","+yearCity[1]+","+sport+","+discipline+","+athlete+","+country+","+gender+","+event+","+medal;
			OlympicDataService olympicService=new OlympicDataService();
			try {
				System.out.println(record);
				olympicService.insertRecord(record);
				System.out.println("record inserted");
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("UserLogin.jsp");
				requestDispatcher.forward(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(options.containsKey("updatePage"))
		{

			Map<String, String[]>display=request.getParameterMap();
			OlympicDataService olympicService=new OlympicDataService();
			List<OlympicDataPojo>displayRecord=new ArrayList<OlympicDataPojo>();
			UpdatePageUtil record=new UpdatePageUtil();
			OlympicDataPojo olympicRecord=record.displaySelectedRecord(display);
			displayRecord=olympicService.displayRecord(olympicRecord);
			
			Map<Integer, String>hostValues=new LinkedHashMap<Integer, String>();
			Map<String, Map<String,List<String>>>sportDisciplineEvent=new HashMap<>();
			Set<String>country=new HashSet<>();
			try {
				hostValues=olympicService.selectHost();
				request.setAttribute("yearList", hostValues);
				sportDisciplineEvent=olympicService.selectDiscipline();
				request.setAttribute("sportList", sportDisciplineEvent);
				country=olympicService.selectCountry();
				request.setAttribute("countryList", country);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			displayRecord.forEach(System.out::println);
			request.setAttribute("displayRecord", displayRecord);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("UpdatePage.jsp");
			requestDispatcher.forward(request,response);
		}
		if(options.containsKey("updateDeleteRecordPage"))
		{
			String oldAthlete="",athlete="",athleteOld="";
			String newAthlete[];
			Iterator<String>it1=options.keySet().iterator();
			while(it1.hasNext())
			{
				String key=it1.next();
				String value[]=options.get(key);
				System.out.println(key+" :: "+value[0]);
				
				if(key.equals("edit"))
				{
					System.out.println("sucess.........");
					Iterator<String>it=options.keySet().iterator();
					while(it.hasNext())
					{
						oldAthlete=it.next();
						newAthlete=options.get(oldAthlete);
						//System.out.println(editB+" :: "+editValue[0]);
						if(oldAthlete.equalsIgnoreCase(value[0]))
						{
							athlete=newAthlete[0];
							athleteOld=oldAthlete;
							System.out.println(oldAthlete+"::"+newAthlete[0]);
						}
					}
					OlympicDataService olympicService=new OlympicDataService();
					
					Map<Integer, String>hostValues=new LinkedHashMap<Integer, String>();
					Map<String, Map<String,List<String>>>sportDisciplineEvent=new HashMap<>();
					Set<String>country=new HashSet<>();
					try {
						hostValues=olympicService.selectHost();
						request.setAttribute("yearList", hostValues);
						sportDisciplineEvent=olympicService.selectDiscipline();
						request.setAttribute("sportList", sportDisciplineEvent);
						country=olympicService.selectCountry();
						request.setAttribute("countryList", country);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					UpdatePageUtil record=new UpdatePageUtil();
					List<OlympicDataPojo>updateRecord=new ArrayList<OlympicDataPojo>();
					OlympicDataPojo olympicData=record.updateRecordTable(options);
					updateRecord=olympicService.updateRecord(olympicData,athleteOld,athlete);
					request.setAttribute("displayRecord", updateRecord);
					RequestDispatcher requestDispatcher=request.getRequestDispatcher("UpdatePage.jsp");
					requestDispatcher.forward(request,response);
				}
				if(key.equals("delete"))
				{
					System.out.println("the value is "+value[0]);
					OlympicDataService olympicService=new OlympicDataService();
				
					Map<Integer, String>hostValues=new LinkedHashMap<Integer, String>();
					Map<String, Map<String,List<String>>>sportDisciplineEvent=new HashMap<>();
					Set<String>country=new HashSet<>();
					try {
						hostValues=olympicService.selectHost();
						request.setAttribute("yearList", hostValues);
						sportDisciplineEvent=olympicService.selectDiscipline();
						request.setAttribute("sportList", sportDisciplineEvent);
						country=olympicService.selectCountry();
						request.setAttribute("countryList", country);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				
					List<OlympicDataPojo>deleteRecord=new ArrayList<OlympicDataPojo>();
					UpdatePageUtil record=new UpdatePageUtil();
					OlympicDataPojo olympicData=record.updateRecordTable(options);
					deleteRecord=olympicService.deleteRecord(olympicData,value[0]);
					request.setAttribute("displayRecord", deleteRecord);
					RequestDispatcher requestDispatcher=request.getRequestDispatcher("UpdatePage.jsp");
					requestDispatcher.forward(request,response);
				}
			}
		}
		if(options.containsKey("searchPage"))
		{
			HttpSession session=request.getSession();
			System.out.println("hey ...search");
			
			// Map<String, String[]> keyValueSet = request.getParameterMap();
			 OlympicDataService olympicService = new OlympicDataService();
			List<OlympicDataPojo> filteredData = new ArrayList<OlympicDataPojo>();
			filteredData = olympicService.filterSort(options);
			session.setAttribute("fullQuery", filteredData);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("DownloadFilteredRecord.jsp");
			requestDispatcher.forward(request,response);
			System.out.println("search and filter.....");
			
		}
		if(options.containsKey("Download"))
		{
			List<OlympicDataPojo> filteredData = new ArrayList<OlympicDataPojo>();
			
			HttpSession session = request.getSession();
			filteredData =(List<OlympicDataPojo>) session.getAttribute("fullQuery");
			session.setAttribute("fullQuery", filteredData);
			System.out.println("download part....");
			OlympicDataService olympicService=new OlympicDataService();
			olympicService.filterDisplay(filteredData);
			session.removeAttribute("fullQuery");
		          RequestDispatcher requestDispatcher=request.getRequestDispatcher("SearchFilter.jsp");
					requestDispatcher.forward(request,response);
		}
		if(options.containsKey("userLogin"))
		{
		String optionSelected=request.getParameter("radio");
			if(optionSelected.equalsIgnoreCase("addRecord"))
			{
			Map<Integer, String>hostValues=new LinkedHashMap<Integer, String>();
			Map<String, Map<String,List<String>>>sportDisciplineEvent=new HashMap<>();
			Set<String>country=new HashSet<>();
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("AddingPage.jsp");
			OlympicDataService olympicService=new OlympicDataService();
			try {
				hostValues=olympicService.selectHost();
				request.setAttribute("yearList", hostValues);
				sportDisciplineEvent=olympicService.selectDiscipline();
				request.setAttribute("sportList", sportDisciplineEvent);
				country=olympicService.selectCountry();
				request.setAttribute("countryList", country);
				requestDispatcher.forward(request,response);
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
	
			else if(optionSelected.equalsIgnoreCase("updateRecord"))
			{
			Map<Integer, String>hostValues=new LinkedHashMap<Integer, String>();
			Map<String, Map<String,List<String>>>sportDisciplineEvent=new HashMap<>();
			Set<String>country=new HashSet<>();
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("UpdatePage.jsp");
			OlympicDataService olympicService=new OlympicDataService();
			try {
				//List<OlympicDataPojo>displayRecord=new ArrayList<OlympicDataPojo>();
				hostValues=olympicService.selectHost();
				request.setAttribute("yearList", hostValues);
				sportDisciplineEvent=olympicService.selectDiscipline();
				request.setAttribute("sportList", sportDisciplineEvent);
				country=olympicService.selectCountry();
				request.setAttribute("countryList", country);
				//request.setAttribute("displayRecord", displayRecord);
				requestDispatcher.forward(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			requestDispatcher.forward(request,response);
			}
			else if(optionSelected.equalsIgnoreCase("searchFilter"))
			{
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("SearchFilter.jsp");
				requestDispatcher.forward(request,response);
			}
			}
		else
		{
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("UserLogin.jsp");
			requestDispatcher.forward(request,response);
		}
	}
}