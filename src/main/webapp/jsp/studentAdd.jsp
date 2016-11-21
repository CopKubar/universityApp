<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Add student</title>
</head>
<body>

<%@include file="menu.jsp"%>

<style>
	.studentAdd{
			position: relative;
            left: 10px;
			top: 60px;
			margin: 0px auto;
	}
</style>

<div class="studentAdd">
	<form action="university" method="post">
		<input type="hidden" name="url" value="studentAddForm">
		<p><label><B>Имя </B> <input type="text" name="firstName"> </label></p>
		<p><label><B>Фамилия </B> <input type="text" name="lastName"> </label></p>
		<p><label><B>Год поступления(yyyy) </B> <input type="text" name="entranceYear"> </label></p>
		<input type="submit" value="Добавить студента">
	</form>
</div>

</body>
</html>