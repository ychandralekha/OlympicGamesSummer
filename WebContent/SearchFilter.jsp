<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search and Filter page</title>
</head>
<link rel="stylesheet" type="text/css" href="userLoginPageStyle.css">
<body>
<h2 align="center">Hey ${userName}, Make your choice!</h2>
<br>
<br>
<form action="OlympicUserOperationsServlet" method="post">
    Enter the Start year:
    <input type="text" name="year" value="" />
    Enter the end year:
     <input type="text" name="year" value="" />
<br><br>
    Enter the Athlete:
    <input type="text" name="athlete" value="" />
<br><br>
    Enter the Sport:
    <input type="text" name="sport" value="" />
<br><br>
    Enter the Country:
    <input type="text" name="country" value="" /> 
<br><br>
    Enter the Gender:
    <input type="text" name="gender" value="" />

<br><br>
<br>
Select The Option to sort:
<select name="sortingSelect" value="">
<option value="year">Year</option>
<option Value="medal">medal</option>
<option value="country">Country</option>
<option value="gender">Gender</option>

</select>
<br>
<br>
    <input type="submit" value="submit" name="searchPage">
   <br>
   <br>
      <a href="UserLogin.jsp">BACK</a> <br>
      <br>
</form>    
</body>
</html>