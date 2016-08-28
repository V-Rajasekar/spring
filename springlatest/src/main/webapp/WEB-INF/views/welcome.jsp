<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Spring 3.0 MVC Series: Welcome - Rajasekar</title>
</head>
<body>
	<form:form commandName="userBean">
		<b>Welcome </b>
		<form:input path="userId" style="border:0px; font:bold" />
	</form:form>
</body>
</html>
