<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Approval page</title>
</head>
<h2 align="center">Hey ${userName}, Let's check out today's approval requests !</h2>
<br>
<h3 style="color:grey">${error}</h3>
<body>
<form action="OlympicAdminServlet" method="post">
<table align="center" border="2" cellpadding="10">
<tr><th>
Usernames
</th><th>Approve</th>
<th>Disapprove</th></tr>
  <c:forEach items="${list}" var="out">
<tr><td>${out}</td><td><input type="radio" name="${out}" value="approve">Approve</td>
<td><input type="radio" name="${out}" value="disapprove">disapprove</td>
</tr>
 </c:forEach>
 <tr><td colspan="3"><input type="submit" value="submit" name="approvalValidation"></td></tr>

</table>
<br>
<br>
<br>
<a href="AdminWelcomePage.jsp">BACK</a>
</form>

</body>
</html>