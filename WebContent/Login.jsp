<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<style>
.container {
  position: relative;
  width: 50%;
}

.image {
  display: block;
  width: 100%;
  height: auto;
}

</style>
<div class="container">
  <img src="images\olympic.jpg" alt="Avatar" class="image">
  </div>
<script type="text/javascript">
function S_click(){
	val1 = document.getElementById("username").value;
	val2 = document.getElementById("password").value;
	 if(val1=="" && val2==""){
	  alert("Fields cannot be empty");
	 } 
	}
</script>
<body background="images\olympic.jpg">
<h2 align="center">Login Page</h2>
<h3 style="color:red">${error}</h3>
<form action="OlympicUserLoginServlet" method="post">
<table align="center">
<tr><td><a href="RegisterPage.jsp">New User?</a></td></tr>
<tr><td>Username: </td>
<td><input type="text" name="username" id="username"></td></tr>
<tr><td>Password: </td>
<td><input type="password" name="password" id="password"></td></tr>
<tr><td><input type="submit" value="Submit" onclick="return S_click();"></td></tr>
</table>
</form>
</body>
</html>