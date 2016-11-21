<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Student update</title>
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
    <form action="university" method="post">
        <input type="hidden" name="url" value="studentUpdateForm">
                <input type="hidden" name="studentId" value="${student.id}">
                <p><label><B>Имя </B> <input type="text" name="firstName" value="${student.firstName}"> </label></p>
                <p><label><B>Фамилия </B><input type="text" name="lastName" value="${student.lastName}"> </label></p>
                <p><label><B>Год поступления(yyyy) </B> <input type="text" name="entranceYear" value="${student.entranceYear}"> </label></p>
        <br>
        <input type="submit" value="Обновить">
    </form>
</div>
</body>
</html>
