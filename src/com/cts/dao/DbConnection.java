package com.cts.dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbConnection {
private DbConnection() {
		
	}

	public static Connection getConnection(){
		Connection con = null;
		try{
		Class.forName("com.mysql.jdbc.Driver");  
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/olympicgamessummer","root","root");
		//return con;
		}  
		catch(Exception e)
		{
		}
		return con;
	}
}
