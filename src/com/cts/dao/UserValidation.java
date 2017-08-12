package com.cts.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cts.constants.ExceptionConstants;
import com.cts.constants.QueryConstants;
import com.cts.dao.DbConnection;
import com.cts.exception.OlympicDataException;

public class UserValidation implements IUserValidations {
	public boolean validateUser(String username, String password)
			throws OlympicDataException {
		boolean status = false;

		int status1 = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = DbConnection.getConnection().prepareStatement(
					QueryConstants.SELECT_USERNAME_PASSWORD);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				status1 = resultSet.getInt("status");
			}

			if (status1 == 1)
				status = true;

		} catch (SQLException e) {
			throw new OlympicDataException(
					ExceptionConstants.REGISTRATION_UNCERTAIN);
		}
		return status;
	}

	public boolean validateIsAdmin(String username, String password)
			throws OlympicDataException {
		boolean status = false;
		String role = "";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = DbConnection.getConnection().prepareStatement(
					QueryConstants.SELECT_USERNAME_PASSWORD);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				role = resultSet.getString("role");
			}
			if (role.equalsIgnoreCase("admin")) {
				status = true;
			}
		} catch (SQLException e) {
			throw new OlympicDataException(
					ExceptionConstants.INCORRECT_CREDENTIALS);
		}
		return status;
	}

	public boolean validateIsUser(String username, String password)
			throws OlympicDataException {
		boolean status = false;
		String role = "";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = DbConnection.getConnection().prepareStatement(
					QueryConstants.SELECT_USERNAME_PASSWORD);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				role = resultSet.getString("role");
			}
			if (role.equalsIgnoreCase("user")) {
				status = true;
			}
		} catch (SQLException e) {
			throw new OlympicDataException(
					ExceptionConstants.INCORRECT_CREDENTIALS);
		}
		return status;
	}
}
