package com.cts.dao;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cts.constants.QueryConstants;
import com.cts.olympicpojo.OlympicDataPojo;

public class OlympicDetailsDao {
    public List<String> loadHostTable(List<OlympicDataPojo>olympicData) throws SQLException
    {
        List<String>hostDetails=new ArrayList<String>();
        System.out.println("loading host table");
        Set<String>uniqueData=new HashSet<String>();
        for(OlympicDataPojo list:olympicData)
        {
            uniqueData.add(list.getYear()+","+list.getCity());
        }
        uniqueData.forEach(System.out::println);
        PreparedStatement preparedStatement=DbConnection.getConnection().prepareStatement(QueryConstants.INSERT_HOST);
        for(String listData:uniqueData)
        {
            String []s=listData.split(",");
            preparedStatement.setInt(1,Integer.parseInt(s[0]));
            preparedStatement.setString(2, s[1]);
            preparedStatement.executeUpdate();
            hostDetails.add(s[0]+","+s[1]);
        }
        hostDetails.forEach(System.out::println);
        return hostDetails;
    }
    public List<String> loadDisciplineTable(List<OlympicDataPojo>olympicData) throws SQLException
    {

        List<String>hostDetails=new ArrayList<String>();
        System.out.println("loading discipline table");
        Set<String>uniqueData=new HashSet<String>();
        for(OlympicDataPojo list:olympicData)
        {
            uniqueData.add(list.getSport()+","+list.getDiscipline()+","+list.getEvent());
        }
        uniqueData.forEach(System.out::println);
        PreparedStatement preparedStatement=DbConnection.getConnection().prepareStatement(QueryConstants.INSERT_DISCIPLINE);
        for(String listData:uniqueData)
        {
            String []s=listData.split(",");
            preparedStatement.setString(1,s[0]);
            preparedStatement.setString(2,s[1]);
            preparedStatement.setString(3, s[2]);
            preparedStatement.executeUpdate();
            hostDetails.add(s[0]+","+s[1]+","+s[2]);
        }
        hostDetails.forEach(System.out::println);
        return hostDetails;
    }
    public List<String> loadAthleteTable(List<OlympicDataPojo>olympicData) 
    {
        List<String>disciplineDetails=new ArrayList<String>();
        PreparedStatement preparedStatement,prepStatement;
        int year=0;
        String event="";
      
        for(OlympicDataPojo disciplineData:olympicData)
        {
        	try{
        		int id=0;
            	preparedStatement=DbConnection.getConnection().prepareStatement(QueryConstants.SELECT_ID);
            	preparedStatement.setString(1,disciplineData.getSport() );
            	preparedStatement.setString(2,disciplineData.getDiscipline());
            	preparedStatement.setString(3,disciplineData.getEvent());
            	ResultSet result=preparedStatement.executeQuery();
            	while(result.next())
            	{
            		id=result.getInt(1);
            	}
	            
            		prepStatement=DbConnection.getConnection().prepareStatement(QueryConstants.INSERT_ATHLETE);
            		
            		prepStatement.setString(1, disciplineData.getAthlete());
            		prepStatement.setString(2, disciplineData.getCountry());
            		prepStatement.setString(3, disciplineData.getGender());
            		prepStatement.setString(4, disciplineData.getMedal());
            		prepStatement.setInt(5, disciplineData.getYear());
            		prepStatement.setInt(6,(id));
            		prepStatement.setString(7, "1");
            		prepStatement.executeUpdate();
            		
            		disciplineDetails.add(disciplineData.getAthlete()+","+disciplineData.getCountry()+","+disciplineData.getGender()+","+disciplineData.getMedal()+","+year+","+event);
       }
       catch(Exception e)
       {
       }
        }
     return disciplineDetails;  
    }
    public Map<Integer, String>selectHostTable() throws SQLException
    {
    	Map<Integer, String>hostValues=new LinkedHashMap<Integer, String>();
    	Statement statement=DbConnection.getConnection().createStatement();
		 ResultSet resultset = statement.executeQuery(QueryConstants.SELECT_HOST) ; 
		 while(resultset.next())
		 {
			 int year=Integer.parseInt(resultset.getString(1));
			 String city=resultset.getString(2);
			 hostValues.put(year,city);
		 }			 
    	return hostValues;
    }
    public Map<String,Map<String, List<String>>>selectDisciplineTable() throws SQLException
    {
    	
    	Map<String, Map<String, List<String>>>outer=new HashMap<>();
		 ResultSet resultset; 
        
		try{			 
		  Statement getRecord=DbConnection.getConnection().createStatement();
		  String query=QueryConstants.SELECT_ALL_FROM_DISCIPLINE;
		  resultset=getRecord.executeQuery(query);
		  while(resultset.next())
		  {
			  String sport=resultset.getString(1);
			  String discipline=resultset.getString(2);
			  String event=resultset.getString(3);
			  if(outer.containsKey(sport))
			  {
				  Map<String, List<String>> inner=outer.get(sport);
				  
				  if(inner.containsKey(discipline))
				  {
					  List<String>eventList=new ArrayList<String>();
					  eventList=inner.get(discipline);
					  eventList.add(event);
					  inner.remove(discipline);
					  inner.put(discipline,eventList);
					  outer.remove(sport);
					  outer.put(sport, inner);
				  }
				  else{
					  List<String>eventList=new ArrayList<String>();
					  eventList.add(event);
					  inner.put(discipline, eventList);
					  outer.remove(sport);
					  outer.put(sport, inner);
				  }
				  
			  }
			  else{
				  List<String>eventList=new ArrayList<String>();
				  eventList.add(event);
				  Map<String, List<String>> inner=new HashMap<String,List<String>>();
				  inner.put(discipline, eventList);
				  outer.put(sport, inner);
			  }
 
		  }
		  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	return outer;
    }
    public Set<String> selectCountryTable() throws SQLException
    {
    	Set<String>countryValues=new HashSet<>();
    	Statement statement=DbConnection.getConnection().createStatement();
		 ResultSet resultset = statement.executeQuery(QueryConstants.SELECT_COUNTRY) ; 
		 while(resultset.next())
		 {
			 countryValues.add(resultset.getString(1));
		 }		
    	return countryValues;
    }
    public boolean insertRecordTable(String listData) 
    {
    	boolean result=false;
    	int id=0;
    	 PreparedStatement preparedStatement;
		try {
             String []s=listData.split(",");
  
         	preparedStatement=DbConnection.getConnection().prepareStatement(QueryConstants.SELECT_ID);
        	preparedStatement.setString(1,s[2] );
        	preparedStatement.setString(2,s[3]);
        	preparedStatement.setString(3,s[7]);
        	ResultSet resultId=preparedStatement.executeQuery();
        	while(resultId.next())
        	{
        		id=resultId.getInt(1);
        	}
        	preparedStatement=DbConnection.getConnection().prepareStatement(QueryConstants.INSERT_ATHLETE);
    		
    		preparedStatement.setString(1,s[4]);
    		preparedStatement.setString(2, s[5]);
    		preparedStatement.setString(3, s[6]);
    		preparedStatement.setString(4, s[8]);
    		preparedStatement.setInt(5, Integer.parseInt(s[0]));
    		preparedStatement.setInt(6,(id));
    		preparedStatement.setString(7, "1");
    		preparedStatement.executeUpdate();
    		result=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }
    public List<OlympicDataPojo> updateRecordTable(OlympicDataPojo data,String oldAthlete,String newAthlete)
    {
    	System.out.println(oldAthlete+" "+newAthlete);
     	OlympicDataPojo olympicData;
   		List<OlympicDataPojo>displaySelected=new ArrayList<>();
   		String sport="",discipline="",event="",country="",gender="",medal="",city="";
   		int year;
   		sport=data.getSport();
   		discipline=data.getDiscipline();
   		event=data.getEvent();
   		year=data.getYear();
   		country=data.getCountry();
   		gender=data.getGender();
   		medal=data.getMedal();
   		city=data.getCity();
    	int id=0;
    	
    	PreparedStatement preparedStatement;
		try {	
 
        	preparedStatement=DbConnection.getConnection().prepareStatement(QueryConstants.SELECT_ID);
       	preparedStatement.setString(1,sport );
       	preparedStatement.setString(2,discipline);
       	preparedStatement.setString(3,event);
       	ResultSet resultId=preparedStatement.executeQuery();
       	while(resultId.next())
       	{
       		id=resultId.getInt(1);
       	}

       	preparedStatement=DbConnection.getConnection().prepareStatement(QueryConstants.UPDATE_ATHLETE);
   		
       	preparedStatement.setString(1, newAthlete);
   		preparedStatement.setInt(2,year);
   		preparedStatement.setInt(3, id);
   		preparedStatement.setString(4, country);
   		preparedStatement.setString(5, gender);
   		preparedStatement.setString(6, medal);
   		preparedStatement.setString(7, oldAthlete);
   		preparedStatement.executeUpdate();

   		preparedStatement=DbConnection.getConnection().prepareStatement(QueryConstants.SELECT_ATHLETE);
   		
   		preparedStatement.setInt(1,year);
   		preparedStatement.setInt(2, id);
   		preparedStatement.setString(3, country);
   		preparedStatement.setString(4, gender);
   		preparedStatement.setString(5, medal);
   		preparedStatement.setString(6,"1");
   		resultId=preparedStatement.executeQuery();
   		
   		while(resultId.next())
   		{
   		olympicData=new OlympicDataPojo(year, city, sport, discipline, resultId.getString(1), country, gender, event, medal);
   		displaySelected.add(olympicData);
   		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	//OlympicDataPojo olympicObject=new OlympicDataPojo(year, city, sport, discipline, athlete, country, gender, event, medal);
    	return displaySelected;
    }
    public List<OlympicDataPojo> displaySelectedRecord(OlympicDataPojo data)
    {
    	List<OlympicDataPojo>displaySelected=new ArrayList<>();
     	OlympicDataPojo olympicData;
   		String sport="",discipline="",event="",country="",gender="",medal="",city="";
   		int year;
   		sport=data.getSport();
   		discipline=data.getDiscipline();
   		event=data.getEvent();
   		year=data.getYear();
   		country=data.getCountry();
   		gender=data.getGender();
   		medal=data.getMedal();
   		city=data.getCity();
    	int id=0;
   	 PreparedStatement preparedStatement;
		try {	
        preparedStatement=DbConnection.getConnection().prepareStatement(QueryConstants.SELECT_ID);
       	preparedStatement.setString(1,sport );
       	preparedStatement.setString(2,discipline);
       	preparedStatement.setString(3,event);
       	ResultSet resultId=preparedStatement.executeQuery();
       	while(resultId.next())
       	{
       		id=resultId.getInt(1);
       	}
       	preparedStatement=DbConnection.getConnection().prepareStatement(QueryConstants.SELECT_ATHLETE);
   		
   		preparedStatement.setInt(1,(year));
   		preparedStatement.setInt(2, id);
   		preparedStatement.setString(3, country);
   		preparedStatement.setString(4, gender);
   		preparedStatement.setString(5, medal);
   		preparedStatement.setString(6,"1");
   		ResultSet resultAthlete=preparedStatement.executeQuery();
   		
   		while(resultAthlete.next())
   		{
   			System.out.println(resultAthlete.getString(1));
   			olympicData=new OlympicDataPojo((year), city, sport, discipline, resultAthlete.getString(1), country, gender, event, medal);
   			displaySelected.add(olympicData);
   		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//displaySelected.forEach(System.out::println);
    	return displaySelected;
    }
    public List<OlympicDataPojo> deleteRecordTable(OlympicDataPojo data,String athlete)
    {
    	OlympicDataPojo olympicData;
   		String sport="",discipline="",event="",country="",gender="",medal="",city="";
   		List<OlympicDataPojo>displaySelected=new ArrayList<>();
  		int year;
   		sport=data.getSport();
   		discipline=data.getDiscipline();
   		event=data.getEvent();
   		year=data.getYear();
   		country=data.getCountry();
   		gender=data.getGender();
   		medal=data.getMedal();
   		city=data.getCity();
   		System.out.println(sport+" "+discipline+" "+event+" "+year+" "+country+" "+gender+" "+medal+" "+athlete);
    	int id=0;
   	 PreparedStatement preparedStatement;
		try {	
 
        	preparedStatement=DbConnection.getConnection().prepareStatement(QueryConstants.SELECT_ID);
       	preparedStatement.setString(1,sport );
       	preparedStatement.setString(2,discipline);
       	preparedStatement.setString(3,event);
       	ResultSet resultId=preparedStatement.executeQuery();
       	while(resultId.next())
       	{
       		id=resultId.getInt(1);
       	}
       	preparedStatement=DbConnection.getConnection().prepareStatement(QueryConstants.UPDATE_ATHLETE_TABLE);
   		
       	preparedStatement.setString(1, "0");
   		preparedStatement.setInt(2,year);
   		preparedStatement.setInt(3, id);
   		preparedStatement.setString(4, country);
   		preparedStatement.setString(5, gender);
   		preparedStatement.setString(6, medal);
   		preparedStatement.setString(7, athlete);
   		preparedStatement.executeUpdate();
   		
   		preparedStatement=DbConnection.getConnection().prepareStatement(QueryConstants.SELECT_ATHLETE);
   		
   		preparedStatement.setInt(1,year);
   		preparedStatement.setInt(2, id);
   		preparedStatement.setString(3, country);
   		preparedStatement.setString(4, gender);
   		preparedStatement.setString(5, medal);
   		preparedStatement.setString(6,"1");
   		ResultSet resultAthlete=preparedStatement.executeQuery();
   		System.out.println(city+" =city");
   		while(resultAthlete.next())
   		{
   			System.out.println(resultAthlete.getString(1));
   			olympicData=new OlympicDataPojo(year, city, sport, discipline, resultAthlete.getString(1), country, gender, event, medal);
   			displaySelected.add(olympicData);
   		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//displaySelected.forEach(System.out::println);
    	return displaySelected;
    }
    public List<OlympicDataPojo> generateSqlQuery(Map<String,String[]>filter)
    {
    			List<OlympicDataPojo>result=new ArrayList<OlympicDataPojo>();
    			String query="";
    			String queryFinal="";
    			String sortQuery="";
    			Iterator<String>it=filter.keySet().iterator();
    			String sportSelect="";
    			List<String>queries=new ArrayList<String>();
    			while(it.hasNext())
    			{ 
    				String key=it.next();
    				String []values=filter.get(key);
    				System.out.println(key+" "+values[0]);

    				if(!key.equalsIgnoreCase("searchPage"))
    				{
    					if(values.length==2)
    					{ 
    						if(values[0]!="" && values[1]!="")
    						{
    						query=" y."+key+" between "+values[0]+" and "+values[1];
    						queries.add(query);
    						}
    					} 
    					else if(values.length==1)
    					{			
    						if(values[0]!="")
    						{
    		    				if(key.equalsIgnoreCase("sport"))
    		    				{
    		    					query=" d.sport like '"+values[0]+"%' ";
    		    					queries.add(query);
    		    				}
    							//System.out.println(values[0]);
    		    				else
    		    				{
    		    					if(!key.equals("sortingSelect"))
    		    					{
    							//System.out.println(values[0]);
    		    						query=" a."+key+" like '"+values[0]+"%' ";
    								//System.out.println(query);
    		    						queries.add(query);
    		    						}
    		    					else
    		    					{
    		    						sortQuery=" order by "+values[0];
    		    					}
    		    				}
    						}
    					}
    				}
    			}
    			for(int i=0;i<queries.size();i++)
    			{
    				if(i==0)
    				{
    				queryFinal=queryFinal+" where "+queries.get(i);
    				}
    				else
    					queryFinal=queryFinal+" and "+queries.get(i);
    			}
    			System.out.println(queryFinal);
    			System.out.println(sportSelect);
    			String fullQuery=QueryConstants.SELECT_ALL;
    			if(queryFinal.equals(""))
    			fullQuery=fullQuery+QueryConstants.DISPLAY_RECORD;
    			else
    				fullQuery=fullQuery+queryFinal+QueryConstants.DISPLAY_RECORDS;
    			
    			fullQuery=fullQuery+sortQuery;
    			System.out.println(fullQuery);
    			try {
    				Statement statement=DbConnection.getConnection().createStatement();
    				 ResultSet resultset = statement.executeQuery(fullQuery); 
    				 while(resultset.next())
    				 {
    					 int year=Integer.parseInt(resultset.getString(1));
    					 String city=resultset.getString(2);
    					 String sport=resultset.getString(3);
    					 String discipline=resultset.getString(4);
    					 String athlete=resultset.getString(5);
    					 String country=resultset.getString(6);
    					 String gender=resultset.getString(7);
    					 String event=resultset.getString(8);
    					 String medal=resultset.getString(9);
    					 OlympicDataPojo pojoObj=new OlympicDataPojo(year,city,sport,discipline,athlete,country,gender,event,medal);
    					 result.add(pojoObj);
    				 }			 
    			} catch (Exception e) {
    		
    			}
    			
    			return result;
    		}
   public boolean filterDisplayRecord(List<OlympicDataPojo>filteredData)
    {
	   boolean result=false;
    	try
    	{
    		FileWriter fw=new FileWriter("D:\\FilteredOlympicData.txt");    
	           for(OlympicDataPojo record:filteredData)
	           {
	        	   String readRecord=record.getYear()+","+record.getCity()+","+record.getSport()+","+record.getDiscipline()+","+record.getAthlete()+","+record.getCountry()+","+record.getGender()+","+record.getEvent()+","+record.getMedal();
	        	   fw.write(readRecord);    
	           }
	           result=true;
	           fw.close();
    	}
    	catch(Exception e){System.out.println(e);}   
    	System.out.println("Success...");
        return result;
    	
    }
}