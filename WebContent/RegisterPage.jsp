<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
</head>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <style>
.container {
  position: relative;
  width: 45%;
}

.image {
  display: block;
  width: 90%;
  height: auto;
}

</style>
<div class="container">
  <img src="images\olympic.jpg" alt="Avatar" class="image">
  </div>
 <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  function validateForm() {

	    var email = document.forms["myForm"]["email"].value;

	    var atpos = email.indexOf("@");
	    var dotpos = email.lastIndexOf(".");
	    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length) {
	        alert("Not a valid e-mail address");
	        return false;
	    }
	}
  </script>
<body background="images\olympic.jpg">
<h2 align="center">Registation Page</h2>
<h3 style="color:red">${error}</h3>
<h6 align="center"><a href="Login.jsp">EXISTING USER? LOG IN!</a></h6>
<form name="myForm" action="OlympicUserRegisterationServlet" method="post" onsubmit="return validateForm();">
<table align="center">
<tr><td>First Name:</td>
<td><input type="text" name="firstName" required/></td>
</tr>
<tr><td>Last Name:</td>
<td><input type="text" name="lastName" required/></td>
</tr>
<tr><td>User Name:</td>
<td><input type="text" name="userName" required/></td>
</tr>
<tr><td>Date of Birth:</td>
<td><input type="text" name="datepicker" id="datepicker" required/></td>
</tr>
<tr><td>Email ID:</td>
<td><input type="text" name="email" required/></td>
</tr>
<tr><td>Phone Number:</td>
<td><input type="text" name="phoneNumber" required/></td>
</tr>
<tr><td>Password:</td>
<td><input type="password" name="password" required/></td>
</tr>
<tr>
<td>
<input type="submit" value="submit"/>
</td>
</tr>
</table>
</form>
</body>
</html>