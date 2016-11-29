<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Студенты</title>
</head>
<body>

<style>
    .tableStudents {
        position: relative;
		bottom: 30px;
		left: 120px;
    }
    .studentAdd{
        position: relative;
		top: -45px;
		left: 210px;
    }
</style>

<%@include file="menu.jsp"%>

<div class="studentAdd">
    <a href="<c:url value='/studentAdd' />">Добавить студента</a>
</div>

<div class="tableStudents">
    <table border="2" bgcolor="#C1CDCD" >
        <th colspan="3">Студенты</th>
        <tr>
            <td align="center"><B>Имя</B></td>
            <td align="center"><B>Фамилия</B></td>
            <td align="center"><B>Дата поступления</B></td>
        </tr>
        <c:forEach items="${studentList}" var="student">
            <c:if test="${student.deleted eq false}">
                <tr>
                    <td align="center">${student.firstName}</td>
                    <td align="center">${student.lastName}</td>
                    <td align="center">${student.entranceYear}</td>
                    <td align="center">
                        <a href="<c:url value='/profile/student/${student.id}' />">Профиль</a>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</div>
</body>
</html>