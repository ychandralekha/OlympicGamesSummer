package com.cts.testing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.cts.exception.OlympicDataException;
import com.cts.service.OlympicService;

public class OlympicUserTesting {
	OlympicService olympicService; 
	List<String> list = new ArrayList<String>();
	@Before
	public void setUp() {
		olympicService = mock(OlympicService.class);
	}
	@Test
	public void testvalidate() throws OlympicDataException
	{
		String username="vp";
		String password="1234"; 
		when(olympicService.validate(username, password)).thenReturn(true);
		assertEquals(true, olympicService.validate(username, password));
	}
	@Test
	public void testvalidateAdmin()throws OlympicDataException
	{
		String username="ycl";
		String password="1234"; 
		when(olympicService.validateAdmin(username, password)).thenReturn(true);
		assertEquals(true, olympicService.validateAdmin(username, password));
	}
	@Test
	public void testvalidateUsers()throws OlympicDataException
	{
		String username="vp";
		String password="1234"; 
		when(olympicService.validateAdmin(username, password)).thenReturn(true);
		assertEquals(true, olympicService.validateAdmin(username, password));
	}
	@Test
	public void testloadUserField()throws OlympicDataException
	{
		Map<String, String[]> userFields=new HashMap<>();
		when(olympicService.loadUserField(userFields)).thenReturn(false);
		assertEquals(false, olympicService.loadUserField(userFields));
	}
	@Test
	public void testapproveUser() throws OlympicDataException
	{
		List<String>list=new ArrayList<>();
		list.add("chandra");
		when(olympicService.approveUser()).thenReturn(list);
		assertEquals(list, olympicService.approveUser());
	}
	@Test
	public void teststatusUpdate()throws OlympicDataException
	{
		List<String> updatedList=new ArrayList<>();
		String query="";
		when(olympicService.statusUpdate(updatedList, query)).thenReturn(false);
		assertEquals(false, olympicService.statusUpdate(updatedList, query));
	}
}
