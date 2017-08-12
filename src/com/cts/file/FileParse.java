package com.cts.file;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cts.olympicpojo.OlympicDataPojo;


public class FileParse {
	public List<OlympicDataPojo> parseData(BufferedReader bufferedReader)  {
		List<OlympicDataPojo>fileData=new ArrayList<OlympicDataPojo>();
		
		String record;
		try {
			while((record=bufferedReader.readLine())!=null)
			{
				String[] records=record.split(",");
				OlympicDataPojo olympicData;
				if(records.length==10)
				{
					String firstName=records[4].replace("\"","");
					String lastName=records[5].replace("\"", "");
					String athlete=firstName+","+lastName;
					olympicData=new OlympicDataPojo(Integer.parseInt(records[0]),records[1].toUpperCase(),records[2].toUpperCase(),records[3].toUpperCase(),athlete.toUpperCase(),records[6].toUpperCase(),records[7].toUpperCase(),records[8].toUpperCase(),records[9].toUpperCase());
				}
				else
					olympicData=new OlympicDataPojo(Integer.parseInt(records[0]),records[1].toUpperCase(),records[2].toUpperCase(),records[3].toUpperCase(),records[4].toUpperCase(),records[5].toUpperCase(),records[6].toUpperCase(),records[7].toUpperCase(),records[8].toUpperCase());
				fileData.add(olympicData);
			}
			Set<String>s=new HashSet<String>();
			for(OlympicDataPojo odp:fileData)
			{
				s.add(odp.getAthlete());
			}
			s.forEach(System.out::println);
			System.out.println(s.size());
			System.out.println(fileData.size());
		} catch (Exception e) {
			
		}
		return fileData;
	}
}
