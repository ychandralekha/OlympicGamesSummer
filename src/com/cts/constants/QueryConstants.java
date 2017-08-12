package com.cts.constants;

public class QueryConstants {
public static final String INSERT_INTO_USER="insert into user values(?,?,?,?,?,?,?,?,?,?)";
public static final String USER_APPROVAL_SELECT="select username from user where status=0 and display=1";
public static final String SELECT_USERNAME_PASSWORD="select * from user where username=? and password=?";
public static final String UPDATE_STATUS="update user set status=1 where username=?";
public static final String UPDATE_USER_DISPLAY="update user set display=0 where username=?";
}
