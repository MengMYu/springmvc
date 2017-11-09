<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your profile</title>
</head>
<body>
	<c:out value="{spitter.username}"></c:out>
	<br/>
	<c:out value="{spitter.firstName}"></c:out>
	<c:out value="{spitterlastName}"></c:out>
	<br/>

</body>
</html>