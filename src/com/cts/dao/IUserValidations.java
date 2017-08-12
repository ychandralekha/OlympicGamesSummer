package com.cts.dao;

import com.cts.exception.OlympicDataException;

public interface IUserValidations {
	 public boolean validateUser(String username,String password) throws OlympicDataException;
	 public boolean validateIsAdmin(String username,String password) throws OlympicDataException;
	 public boolean validateIsUser(String username,String password) throws OlympicDataException;
}
