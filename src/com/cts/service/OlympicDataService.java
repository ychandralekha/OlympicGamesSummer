package com.cts.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cts.dao.OlympicDetailsDao;
import com.cts.olympicpojo.OlympicDataPojo;

public class OlympicDataService {
public List<String> loadHost(List<OlympicDataPojo>olympicData) throws SQLException
{
	OlympicDetailsDao olympicDetails=new OlympicDetailsDao();
	return olympicDetails.loadHostTable(olympicData);
}
public List<String> loadDiscipline(List<OlympicDataPojo>olympicData) throws SQLException
{
	OlympicDetailsDao olympicDetails=new OlympicDetailsDao();
	return olympicDetails.loadDisciplineTable(olympicData);
}
public List<String> loadAthlete(List<OlympicDataPojo>olympicData) throws SQLException
{
	OlympicDetailsDao olympicDetails=new OlympicDetailsDao();
	return olympicDetails.loadAthleteTable(olympicData);
}
public Map<Integer, String>selectHost() throws SQLException
{
	OlympicDetailsDao olympicDetails=new OlympicDetailsDao();
	return olympicDetails.selectHostTable();	
}
public Map<String,Map<String, List<String>>>selectDiscipline() throws SQLException
{
	OlympicDetailsDao olympicDetails=new OlympicDetailsDao();
	return olympicDetails.selectDisciplineTable();	
}
public Set<String> selectCountry() throws SQLException
{
	OlympicDetailsDao olympicDetails=new OlympicDetailsDao();
	return olympicDetails.selectCountryTable();
}
public boolean insertRecord(String addData) throws SQLException
{
	OlympicDetailsDao olympicDetails=new OlympicDetailsDao();
	return olympicDetails.insertRecordTable(addData);
}
public List<OlympicDataPojo> updateRecord(OlympicDataPojo data,String oldAthlete,String newAthlete)
{
	OlympicDetailsDao olympicDetails=new OlympicDetailsDao();
	return olympicDetails.updateRecordTable(data,oldAthlete,newAthlete);
}
public List<OlympicDataPojo>displayRecord(OlympicDataPojo data)
{
	OlympicDetailsDao olympicDetails=new OlympicDetailsDao();
	return olympicDetails.displaySelectedRecord(data);
}
public List<OlympicDataPojo> deleteRecord(OlympicDataPojo data,String athlete)
{
	OlympicDetailsDao olympicDetails=new OlympicDetailsDao();
	return olympicDetails.deleteRecordTable(data,athlete);
}
public List<OlympicDataPojo> filterSort(Map<String,String[]>filter)
{
	OlympicDetailsDao olympicDetails=new OlympicDetailsDao();
	return olympicDetails.generateSqlQuery(filter);
}
public boolean filterDisplay(List<OlympicDataPojo>filteredData)
{
	OlympicDetailsDao olympicDetails=new OlympicDetailsDao();
	return olympicDetails.filterDisplayRecord(filteredData);
}
}
