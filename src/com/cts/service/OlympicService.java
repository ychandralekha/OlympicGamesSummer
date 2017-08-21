package com.cts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cts.dao.AdminValidation;
import com.cts.dao.UserValidation;
import com.cts.exception.OlympicDataException;
import com.cts.olympicpojo.OlympicDataPojo;
import com.cts.olympicpojo.OlympicUserPojo;

public class OlympicService {
	public boolean validate(String username, String password)
			throws OlympicDataException {
		boolean status = false;
		UserValidation userValidation = new UserValidation();
		if (userValidation.validateUser(username, password))
			status = true;
		return status;
	}

	public boolean validateAdmin(String username, String password)
			throws OlympicDataException {
		boolean status = false;
		UserValidation userValidation = new UserValidation();
		if (userValidation.validateIsAdmin(username, password))
			status = true;
		return status;
	}

	public boolean validateUsers(String username, String password)
			throws OlympicDataException {
		boolean status = false;
		UserValidation userValidation = new UserValidation();
		if (userValidation.validateIsUser(username, password))
			status = true;
		return status;
	}

	public boolean loadUserField(OlympicUserPojo olympicPojo)
			throws OlympicDataException {
		boolean status = false;
		
		AdminValidation adminValidation = new AdminValidation();
		status = adminValidation.loadUserData(olympicPojo);
		return status;
	}

	public List<String> approveUser() throws OlympicDataException {
		AdminValidation adminValidation = new AdminValidation();
		List<String> userList = new ArrayList<String>();
		userList = adminValidation.approveUser();
		return userList;
	}

	public boolean statusUpdate(List<String> approvedList, String query)
			throws OlympicDataException {
		AdminValidation adminValidation = new AdminValidation();
		// List<String>userList=new ArrayList<String>();
		boolean status = adminValidation.updateStatus(approvedList, query);
		return status;
	}
}
