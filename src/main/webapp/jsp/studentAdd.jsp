<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Add student</title>
</head>
<body>

<%@include file="menu.jsp"%>

<div align="center">
	<form action="university" method="post">
		<input type="hidden" name="url" value="studentAddForm">
		<h4>Имя </h4> <input type="text" name="firstName"> <br/>
		<h4>Фамилия </h4> <input type="text" name="lastName"> <br/>
		<h4>Год поступления(yyyy) </h4> <input type="text" name="entranceYear"> <br/>
		<input type="submit" value="Добавить студента">
	</form>
</div>

</body>
</html>