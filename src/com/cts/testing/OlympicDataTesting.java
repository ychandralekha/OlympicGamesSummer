package com.cts.testing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.cts.olympicpojo.OlympicDataPojo;
import com.cts.service.OlympicDataService;

public class OlympicDataTesting {

	OlympicDataService olympicService; 
	List<String> list = new ArrayList<String>();
	@Before
	public void setUp() {
		olympicService = mock(OlympicDataService.class);
		list.add("2000,Sydney,Aquatics,Diving,\"SAUTIN, Dmitry\",Rus,Men,10M Platform,Bronze");
		list.add("2000,Sydney,Aquatics,Diving,\"XIONG, Ni\",CHN,Men,3M Springboard,Gold");
	}
	@Test
	public void testLoadHost() throws SQLException
	{
		List<OlympicDataPojo> data = new ArrayList<OlympicDataPojo>();
		OlympicDataPojo op=null;
		op = new OlympicDataPojo(2000, "Sydney", "Aquatics", "Diving",
				"SAUTIN, Dmitry", "Rus", "Men", "10M Platform", "Bronze");
		data.add(op);
		op = new OlympicDataPojo(2004, "Athens", "Aquatics", "Diving", "XIONG, Ni",
				"CHN", "Men", "3M Springboard", "Gold");
		data.add(op);
		when(olympicService.loadHost(data)).thenReturn(list);
	}
	@Test
	public void testLoadDiscipline() throws SQLException
	{
		List<OlympicDataPojo> data = new ArrayList<OlympicDataPojo>();
		OlympicDataPojo op=null;
		op = new OlympicDataPojo(2000, "Sydney", "Aquatics", "Diving",
				"SAUTIN, Dmitry", "Rus", "Men", "10M Platform", "Bronze");
		data.add(op);
		op = new OlympicDataPojo(2004, "Athens", "Aquatics", "Diving", "XIONG, Ni",
				"CHN", "Men", "3M Springboard", "Gold");
		data.add(op);
		when(olympicService.loadHost(data)).thenReturn(list);
	}
	@Test
	public void testLoadAthlete() throws SQLException
	{
		List<OlympicDataPojo> data = new ArrayList<OlympicDataPojo>();
		OlympicDataPojo op=null;
		op = new OlympicDataPojo(2000, "Sydney", "Aquatics", "Diving",
				"SAUTIN, Dmitry", "Rus", "Men", "10M Platform", "Bronze");
		data.add(op);
		op = new OlympicDataPojo(2004, "Athens", "Aquatics", "Diving", "XIONG, Ni",
				"CHN", "Men", "3M Springboard", "Gold");
		data.add(op);
		when(olympicService.loadHost(data)).thenReturn(list);
	}
	@Test
	public void testselectHost() throws SQLException
	{
		Map<Integer, String>host=new HashMap<Integer, String>();
		when(olympicService.selectHost()).thenReturn(host);
		
	}
	@Test
	public void selectDiscipline() throws SQLException
	{
		Map<String,Map<String, List<String>>>discipline=new HashMap<>();
		when(olympicService.selectDiscipline()).thenReturn(discipline);
	}
	@Test
	public void selectCountry() throws SQLException
	{
		Set<String>country=new HashSet<>();
		when(olympicService.selectCountry()).thenReturn(country);
	}
	@Test
	public void testinsertRecord() throws SQLException
	{
		String record="2000,Sydney,Aquatics,Diving,\"SAUTIN, Dmitry\",Rus,Men,10M Platform,Bronze";
		when(olympicService.insertRecord(record)).thenReturn(true);
		
	}
//	public List<OlympicDataPojo> updateRecord(Map<String, String[]> data)
//	{
//		
//	}
//	public List<OlympicDataPojo>displayRecord(Map<String, String[]> data)
//	{
//		
//	}
//	public boolean deleteRecord(Map<String, String[]> data)
//	{
//		
//	}
//	public List<OlympicDataPojo> deleteRemaining(Map<String, String[]> data)
//	{
//		
//	}
//	public List<OlympicDataPojo> filterSort(Map<String,String[]>filter)
//	{
//		
//	}
//	public boolean filterDisplay(List<OlympicDataPojo>filteredData)
//	{
//		
//	}
}
