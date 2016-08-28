<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	${message}
	<form:form method="get" commandName="userBean" action="signup.html">
		<table>
			<tr>
				<td>User Id:</td>
				<td><form:input path="userId" /></td>
				<td><form:errors path="userId" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password" showPassword="true" /></td>
				<td><form:errors path="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Login" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>