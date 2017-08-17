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
		list.add("2004,Athens,Aquatics,Diving,\"XIONG, Ni\",CHN,Men,3M Springboard,Gold");
	}
	@Test
	public void testLoadHost() throws SQLException
	{
		List<OlympicDataPojo> data = new ArrayList<OlympicDataPojo>();
		OlympicDataPojo op=null;
		op = new OlympicDataPojo(2000, "Sydney", "Aquatics", "Diving",
				"\"SAUTIN, Dmitry\"", "Rus", "Men", "10M Platform", "Bronze");
		data.add(op);
		op = new OlympicDataPojo(2004, "Athens", "Aquatics", "Diving", "\"XIONG, Ni\"",
				"CHN", "Men", "3M Springboard", "Gold");
		data.add(op);
		when(olympicService.loadHost(data)).thenReturn(list);
		assertEquals(list, olympicService.loadHost(data));
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
		assertEquals(list, olympicService.loadHost(data));
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
		assertEquals(list, olympicService.loadHost(data));
	}
	@Test
	public void testselectHost() throws SQLException
	{
		Map<Integer, String>host=new HashMap<Integer, String>();
		when(olympicService.selectHost()).thenReturn(host);
		assertEquals(host, olympicService.selectHost());
	}
	@Test
	public void selectDiscipline() throws SQLException
	{
		Map<String,Map<String, List<String>>>discipline=new HashMap<>();
		when(olympicService.selectDiscipline()).thenReturn(discipline);
		assertEquals(discipline, olympicService.selectDiscipline());
	}
	@Test
	public void selectCountry() throws SQLException
	{
		Set<String>country=new HashSet<>();
		when(olympicService.selectCountry()).thenReturn(country);
		assertEquals(country, olympicService.selectCountry());
	}
	@Test
	public void testinsertRecord() throws SQLException
	{
		String record="2000,Sydney,Aquatics,Diving,\"SAUTIN, Dmitry\",Rus,Men,10M Platform,Bronze";
		when(olympicService.insertRecord(record)).thenReturn(true);
		assertEquals(true, olympicService.insertRecord(record));
	}
	@Test
	public void testUpdateRecord()
	{
		List<OlympicDataPojo>updatedRecord=new ArrayList<>();
		Map<String, String[]>updateParameters=new HashMap<>();
		String input[] = new String[1];
		input[0]="Aquatics";
		updateParameters.put("sportId",input);
		input[0]="Diving";
		updateParameters.put("disciplineId",input);
		input[0]="10M Platform";
		updateParameters.put("eventId",input);
		input[0]="2000";
		updateParameters.put("yearId",input);
		input[0]="Rus";
		updateParameters.put("countryId",input);
		input[0]="Men";
		updateParameters.put("genderId",input);
		input[0]="Bronze";
		updateParameters.put("medalId",input);
		input[0]="Sydney";
		updateParameters.put("cityId",input);
		when(olympicService.updateRecord(updateParameters,"SAUTIN, Dmitry","chandu")).thenReturn(updatedRecord);
		assertEquals(updatedRecord, olympicService.updateRecord(updateParameters,"SAUTIN, Dmitry","chandu"));
	}
	@Test
	public void testdisplayRecord()
	{
		List<OlympicDataPojo>updatedRecord=new ArrayList<>();
		Map<String, String[]>updateParameters=new HashMap<>();
		String input[] = new String[1];
		input[0]="Aquatics";
		updateParameters.put("sportselect",input);
		input[0]="Diving";
		updateParameters.put("disciplineselect",input);
		input[0]="10M Platform";
		updateParameters.put("eventselect",input);
		input[0]="2000=Sydney";
		updateParameters.put("yearselect",input);
		input[0]="Rus";
		updateParameters.put("countryselect",input);
		input[0]="Men";
		updateParameters.put("genderselect",input);
		input[0]="Bronze";
		updateParameters.put("medalselect",input);
		when(olympicService.displayRecord(updateParameters)).thenReturn(updatedRecord);
		assertEquals(updatedRecord, olympicService.displayRecord(updateParameters));
	}
	@Test
	public void deleteRecord()
	{
		List<OlympicDataPojo>updatedRecord=new ArrayList<>();
		Map<String, String[]>updateParameters=new HashMap<>();
		String input[] = {"Aquatics"};
		input[0]="Aquatics";
		updateParameters.put("sportId",input);
		input[0]="Diving";
		updateParameters.put("disciplineId",input);
		input[0]="10M Platform";
		updateParameters.put("eventId",input);
		input[0]="2000";
		updateParameters.put("yearId",input);
		input[0]="Rus";
		updateParameters.put("countryId",input);
		input[0]="Men";
		updateParameters.put("genderId",input);
		input[0]="Bronze";
		updateParameters.put("medalId",input);
		input[0]="Sydney";
		updateParameters.put("cityId",input);
		when(olympicService.deleteRecord(updateParameters,"SAUTIN, Dmitry")).thenReturn(updatedRecord);
		assertEquals(updatedRecord, olympicService.deleteRecord(updateParameters,"SAUTIN, Dmitry"));
	}
	@Test
	public void testfilterSort()
	{
		List<OlympicDataPojo>updatedRecord=new ArrayList<>();
		Map<String, String[]>updateParameters=new HashMap<>();
		String[] testValue = new String[1];
		testValue[0] = "P";
		updateParameters.put("country", testValue);
		String[] testValue1 = new String[1];
		testValue1[0] = "gender"; 
		updateParameters.put("sortingSelect", testValue1);
		when(olympicService.filterSort(updateParameters)).thenReturn(updatedRecord);
		assertEquals(updatedRecord, olympicService.filterSort(updateParameters));
	}
	@Test
	public void testfilterDisplay()
	{
		List<OlympicDataPojo>filteredData=new ArrayList<>();
		List<OlympicDataPojo> data = new ArrayList<OlympicDataPojo>();
		OlympicDataPojo op=null;
		op = new OlympicDataPojo(2000, "Sydney", "Aquatics", "Diving",
				"SAUTIN, Dmitry", "Rus", "Men", "10M Platform", "Bronze");
		data.add(op);
		when(olympicService.filterDisplay(filteredData)).thenReturn(true);
		assertEquals(true, olympicService.filterDisplay(filteredData));
	}
}
