<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<html>
<head>
    <title>Error student add</title>
</head>
<body>
<%@include file="menu.jsp"%>

<h4 align="center" style="color:#FF0000">Проверте правильность ввода</h4>

</br>
</br>
<div align="center">
    <form action="university" method="post">
        <input type="hidden" name="url" value="studentAddForm">
        <h4>Имя</h4> <input type="text" name="firstName" value="${requestScope.firstName}">
        <h4>Фамилия</h4> <input type="text" name="lastName" value="${requestScope.lastName}">
        <h4>Год поступления</h4> <input type="text" name="entranceYear" value="${requestScope.entranceYear}">
        <br/>
        <input type="submit" value="Добавить студента">
    </form>
</div>
</body>
</html>
