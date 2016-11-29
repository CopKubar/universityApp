<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<title>Студент</title>
</head>
<body>


<style>
	.studentAdd{
		position: relative;
		left: 10px;
		top: 40px;
		margin: 0px auto;
	}
	.error {
		color: #ff0000;
	}
	.profile{
		position: relative;
		top: 30px;
		left: 10px;
	}
</style>

<%@include file="menu.jsp"%>

<c:if test="${edit eq true}">
	<div class="profile">
		<a href="<c:url value='/profile/student/${student.id}' />">Профиль</a>
	</div>
</c:if>


<div class="studentAdd">
	<c:choose>
		<c:when test="${edit eq true}">
			<h2>Форма редактирования студента</h2>
		</c:when>
		<c:otherwise>
			<h2>Форма добавления студента</h2>
		</c:otherwise>
	</c:choose>
		<form:form method="POST" modelAttribute="student">
			<form:input type="hidden" path="id" id="id"/>
			<table>
				<tr>
					<td><label for="firstName">Имя: </label> </td>
					<td><form:input path="firstName" id="firstName"/></td>
					<td><form:errors path="firstName" cssClass="error"/></td>
				</tr>

				<tr>
					<td><label for="lastName">Фамилия: </label> </td>
					<td><form:input path="lastName" id="lastName"/></td>
					<td><form:errors path="lastName" cssClass="error"/></td>
				</tr>

				<tr>
					<td><label for="entranceYear">Дата поступления(dd/mm/yyyy): </label> </td>
					<td><form:input path="entranceYear" id="entranceYear"/></td>
					<td><form:errors path="entranceYear" cssClass="error"/></td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<c:choose>
							<c:when test="${edit eq true}">
								<input type="submit" value="Обновить"/>
							</c:when>
							<c:otherwise>
								<input type="submit" value="Добавить"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
		</form:form>
</div>




</body>
</html>