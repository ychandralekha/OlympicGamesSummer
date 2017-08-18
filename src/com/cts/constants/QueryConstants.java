package com.cts.constants;

public class QueryConstants {
public static final String INSERT_INTO_USER="insert into user values(?,?,?,?,?,?,?,?,?,?)";
public static final String USER_APPROVAL_SELECT="select username from user where status=0 and display=1";
public static final String SELECT_USERNAME_PASSWORD="select * from user where username=? and password=?";
public static final String UPDATE_STATUS="update user set status=1 where username=?";
public static final String UPDATE_USER_DISPLAY="update user set display=0 where username=?";
public static final String INSERT_HOST="insert into host values(?,?)";
public static final String INSERT_DISCIPLINE="insert into discipline(sport,discipline,event) values(?,?,?)";
public static final String SELECT_ID="select id from discipline where sport=? and discipline=? and event=?";
public static final String INSERT_ATHLETE="insert into athlete values(?,?,?,?,?,?,?)";
public static final String SELECT_HOST="select * from host";
public static final String SELECT_ALL_FROM_DISCIPLINE="select sport,discipline,event from discipline";
public static final String SELECT_COUNTRY="select country from athlete";
public static final String UPDATE_ATHLETE="update athlete set athlete=? where year=? and event_id=? and country=? and gender=? and medal=? and athlete=?";
public static final String SELECT_ATHLETE="select athlete from athlete where year=? and event_id=? and country=? and gender=? and medal=? and display_id=?";
public static final String UPDATE_ATHLETE_TABLE="update athlete set display_id=? where year=? and event_id=? and country=? and gender=? and medal=? and athlete=?";
public static final String SELECT_ALL="select y.year,y.city,d.sport,d.discipline,a.athlete,a.country,a.gender,d.event,a.medal from host y,discipline d,athlete a";
public static final String DISPLAY_RECORD=" where y.year=a.year and a.event_id=d.id and a.display_id=\"1\"";
public static final String DISPLAY_RECORDS=" and y.year=a.year and a.event_id=d.id and a.display_id=\"1\"";
}
