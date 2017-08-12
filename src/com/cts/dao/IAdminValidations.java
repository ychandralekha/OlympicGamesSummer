package com.cts.dao;

import java.util.List;

import com.cts.exception.OlympicDataException;
import com.cts.olympicpojo.OlympicUserPojo;

public interface IAdminValidations {
	public boolean loadUserData(OlympicUserPojo olympicUserObject) throws OlympicDataException;
	 public List<String> approveUser() throws OlympicDataException;
	 public boolean updateStatus(List<String>updatedList,String query) throws OlympicDataException;
	 
}
