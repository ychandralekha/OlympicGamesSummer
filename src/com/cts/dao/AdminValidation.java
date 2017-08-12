package com.cts.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cts.constants.ExceptionConstants;
import com.cts.constants.QueryConstants;
import com.cts.exception.OlympicDataException;
import com.cts.olympicpojo.OlympicUserPojo;

public class AdminValidation implements IAdminValidations {
	public boolean loadUserData(OlympicUserPojo olympicUserObject)
			throws OlympicDataException {

		boolean status = false;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = DbConnection.getConnection().prepareStatement(
					QueryConstants.INSERT_INTO_USER);

			preparedStatement.setString(1, olympicUserObject.getFirstName());
			preparedStatement.setString(2, olympicUserObject.getLastName());
			preparedStatement.setString(3, olympicUserObject.getUserName());
			preparedStatement.setString(4, olympicUserObject.getDatePicker());
			preparedStatement.setString(5, olympicUserObject.getEmail());
			preparedStatement.setString(6, olympicUserObject.getPhoneNumber());
			preparedStatement.setString(7, olympicUserObject.getPassword());
			preparedStatement.setString(8, olympicUserObject.getRole());
			preparedStatement.setInt(9, olympicUserObject.getStatus());
			preparedStatement.setInt(10, olympicUserObject.getDisplay());
			preparedStatement.executeUpdate();
			status = true;
		} catch (SQLException e) {
			throw new OlympicDataException(
					ExceptionConstants.REGISTRATION_FAILURE);
		}
		System.out.println(status);
		return status;
	}

	public List<String> approveUser() throws OlympicDataException {
		Statement statement;
		List<String> userList = new ArrayList<String>();
		try {
			statement = DbConnection.getConnection().createStatement();
			ResultSet resultSet = statement
					.executeQuery(QueryConstants.USER_APPROVAL_SELECT);
			while (resultSet.next()) {
				String username = resultSet.getString(1);
				userList.add(username);
			}
		} catch (SQLException e) {
			throw new OlympicDataException(ExceptionConstants.APPROVAL_NOT_SENT);
		}
		return userList;
	}

	public boolean updateStatus(List<String> updatedList, String query)
			throws OlympicDataException {
		boolean status = false;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = DbConnection.getConnection().prepareStatement(
					query);
			for (String updatelist : updatedList) {
				preparedStatement.setString(1, updatelist);
				preparedStatement.executeUpdate();
			}
			status = true;

		} catch (SQLException e) {
			throw new OlympicDataException(ExceptionConstants.USER_NOT_APPROVED);
		}
		return status;
	}
}
