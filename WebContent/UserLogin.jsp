<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login</title>
</head>
<body>
<h2 align="center">Hey ${userName}! What do you want to do today?</h2>
<form action="OlympicUserOperationsServlet" method="post">
<table>
<tr>
<td>
<input type="radio" name="radio" value="addRecord" checked/>
</td>
<td> Add a record.
</td>
</tr>
<tr>
<td>
<input type="radio" name="radio" value="updateRecord"/>
</td>
<td> Update/delete a record.
</td>
</tr>
<tr>
<td>
<input type="radio" name="radio" value="searchFilter" />
</td>
<td> Search and filter.
</td>
</tr>
<tr><td><input type="submit" value="submit" name="userLogin"/></td></tr>
</table>
</form>

</body>
</html>