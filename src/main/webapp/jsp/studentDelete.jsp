<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student delete</title>
</head>
<body>

<%@include file="menu.jsp"%>

<style>
	.studentDelete{
			position: relative;
            bottom: 70px;
            left: 200px;
	}
</style>

<div class="studentDelete">
	 <h4 >Отчиcлить студента ?</h4>
    <form action="university" method="post">
        <input type="hidden" name="url" value="studentDeleteForm">
        <input type="hidden" name="studentId" value="${studentId}">
        <input type="radio" name="unswer" value="yes"> Да
        <input type="radio" name="unswer" value="no"> Нет
        <br/><br/>
        <input type="submit" value="Подтвердить">
    </form>
</div>
</body>
</html>
