<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<html>
<head>
    <title>Student update</title>
</head>
<body>
<%@include file="menu.jsp"%>

</br>
</br>

<div align="center">
    <form action="university" method="post">
        <input type="hidden" name="url" value="studentUpdateForm">
                <input type="hidden" name="studentId" value="${student.id}">
                <h4>Имя</h4> <input type="text" name="firstName" value="${student.firstName}">
                <h4>Фамилия</h4> <input type="text" name="lastName" value="${student.lastName}">
                <h4>Год поступления</h4> <input type="text" name="entranceYear" value="${student.entranceYear}">
        <br/>
        <input type="submit" value="Обновить">
    </form>
</div>
</body>
</html>
