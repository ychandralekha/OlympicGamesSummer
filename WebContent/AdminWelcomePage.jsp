<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Welcome Page</title>
</head>
<body>
<h2 align="center">Welcome ${userName}!</h2>

<h3 style="color:grey">${error}</h3>
<form action="OlympicAdminServlet" method="post">
<table>
<tr>
<td>
<input type="radio" name="radioSelect" value="uploadData" checked/>
</td>
<td> Lets Upload !
</td>
</tr>
<tr>
<td>
<input type="radio" name="radioSelect" value="approveUsers"/>
</td>
<td> Lets do some approvals today !</td>
</tr>
<tr><td><input type="submit" value="submit"/></td></tr>
</table>
<br>
<br>
<a href="Login.jsp">LOG OUT</a>
</form>
</body>
</html>