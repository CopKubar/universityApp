<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error student update</title>
</head>
<body>
<%@include file="menu.jsp"%>

<style>
	.studentUpdate{
			position: relative;
            left: 10px;
			top: 30px;
	}
</style>

</br>
</br>

<div class="studentUpdate">
<h4  style="color:#FF0000">Проверте правильность ввода</h4>
    <form action="university" method="post">
        <input type="hidden" name="url" value="studentUpdateForm">
        <input type="hidden" name="studentId" value="${requestScope.studentId}">
        <p><label><B>Имя </B> <input type="text" name="firstName" value="${requestScope.firstName}"></label></p>
        <p><label><B>Фамилия </B><input type="text" name="lastName" value="${requestScope.lastName}"></label></p>
        <p><label><B>Год поступления(yyyy) </B> <input type="text" name="entranceYear" value="${requestScope.entranceYear}"></label></p>
        <br/>
        <input type="submit" value="Обновить">
    </form>
</div>
</body>
</html>
