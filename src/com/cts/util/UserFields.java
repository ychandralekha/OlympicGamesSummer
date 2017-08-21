package com.cts.util;

import java.util.Map;

import com.cts.olympicpojo.OlympicUserPojo;

public class UserFields {

	public OlympicUserPojo userFields(Map<String, String[]> userFields)
	{
		String firstName="",LastName="",userName="",datepicker="",email="",phoneNumber="",password="";
		if(userFields.get("firstName")[0].equals(""))
		{
			
		}
		OlympicUserPojo olympicPojo = new OlympicUserPojo(
				userFields.get("firstName")[0], userFields.get("lastName")[0],
				userFields.get("userName")[0], userFields.get("datepicker")[0],
				userFields.get("email")[0], userFields.get("phoneNumber")[0],
				userFields.get("password")[0]);
		return olympicPojo;
	}
}
