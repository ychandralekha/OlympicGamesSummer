package com.cts.util;

import java.util.Iterator;
import java.util.Map;

import com.cts.olympicpojo.OlympicDataPojo;

public class UpdatePageUtil {

	 public OlympicDataPojo updateRecordTable(Map<String,String[]> data)
	 {
		 String sport="",discipline="",event="",year="",country="",gender="",medal="",city="";
		 Iterator<String>it=data.keySet().iterator();
	   		while(it.hasNext())
	   		{
	   			String key=it.next();
	   			String[] value=data.get(key);
	   			if(key.equalsIgnoreCase("sportId"))
	   				sport=value[0];
	   			if(key.equalsIgnoreCase("disciplineId"))
	   				discipline=value[0];
	   			if(key.equalsIgnoreCase("eventId"))
	   				event=value[0];
	   			if(key.equalsIgnoreCase("yearId"))
	   				year=value[0];
	   			if(key.equalsIgnoreCase("countryId"))
	   				country=value[0];
	   			if(key.equalsIgnoreCase("genderId"))
	   				gender=value[0];
	   			if(key.equalsIgnoreCase("medalId"))
	   				medal=value[0];
	   			if(key.equalsIgnoreCase("cityId"))
	   				city=value[0];
	   		}
	   		OlympicDataPojo olympicData=new OlympicDataPojo(Integer.parseInt(year), city, sport, discipline, null, country, gender, event, medal);	
		return olympicData;
		 
	 }
	 public OlympicDataPojo displaySelectedRecord(Map<String,String[]> data)
	 {
	 String sport="",discipline="",event="",year="",country="",gender="",medal="",city="";
		Iterator<String>it=data.keySet().iterator();
		while(it.hasNext())
		{
			String key=it.next();
			String[] value=data.get(key);
			if(key.equalsIgnoreCase("sportselect"))
				sport=value[0];
			if(key.equalsIgnoreCase("disciplineselect"))
				discipline=value[0];
			if(key.equalsIgnoreCase("eventselect"))
				event=value[0];
			if(key.equalsIgnoreCase("yearselect"))
			{
				String yearCity[]=value[0].split("=");
				year=yearCity[0];
				city=yearCity[1];
			}
			if(key.equalsIgnoreCase("countryselect"))
				country=value[0];
			if(key.equalsIgnoreCase("genderselect"))
				gender=value[0];
			if(key.equalsIgnoreCase("medalselect"))
				medal=value[0];
		}
		OlympicDataPojo olympicData=new OlympicDataPojo(Integer.parseInt(year), city, sport, discipline, null, country, gender, event, medal);	
		return olympicData;
	 }
}
