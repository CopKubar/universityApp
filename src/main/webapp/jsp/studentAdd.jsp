<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Add student</title>
</head>
<body>

<%@include file="menu.jsp"%>

<c:if test="${sessionScope.firstName ne null}">
	<h4 align="center" style="color:#FF0000">Проверте правильность ввода</h4>
</c:if>

<div align="center">
	<form action="university" method="post">
		<input type="hidden" name="url" value="studentAddForm">
		<c:choose>
			<c:when test="${sessionScope.firstName ne null}">
				<h4>Имя </h4> <input type="text" name="firstName" value="${sessionScope.firstName}"> <br/>
			</c:when>
			<c:otherwise>
				<h4>Имя </h4> <input type="text" name="firstName"> <br/>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${sessionScope.lastName ne null}">
				<h4>Фамилия </h4> <input type="text" name="lastName" value="${sessionScope.lastName}"> <br/>
			</c:when>
			<c:otherwise>
				<h4>Фамилия </h4> <input type="text" name="lastName"> <br/>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${sessionScope.entranceYear ne null}">
				<h4>Год поступления </h4> <input type="text" name="entranceYear" value="${sessionScope.entranceYear}"> <br/>
			</c:when>
			<c:otherwise>
				<h4>Год поступления(yyyy) </h4> <input type="text" name="entranceYear"> <br/><br/>
			</c:otherwise>
		</c:choose>
		<br/>
		<input type="submit" value="Добавить студента">
	</form>
</div>

</body>
</html>