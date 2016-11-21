<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Students</title>
</head>
<body>

<style>
    .tableStudents {
        position: relative;
        bottom: 100px;
        left: 230px;
    }
    .studentAdd{
        position: relative;
        top: 10px;
        left: 10px;
    }
</style>
	
<%@include file="menu.jsp"%>

<div class="studentAdd">
    <form action="university" method="get">
        <input type="hidden" name="url" value="studentAdd">
        <input type="submit" value="Добавить студента">
    </form>
</div>
    
<div class="tableStudents">
    <table border="2" bgcolor="#C1CDCD" >
        <th colspan="3">Студенты</th>
        <tr>
            <td align="center"><B>Имя</B></td>
            <td align="center"><B>Фамилия</B></td>
            <td align="center"><B>Год поступления</B></td>
        </tr>
        <c:forEach items="${list}" var="student">
            <c:if test="${student.deleted eq false}">
                <tr>
                    <td align="center">${student.firstName}</td>
                    <td align="center">${student.lastName}</td>
                    <td align="center">${student.entranceYear}</td>
                    <td align="center">
                        <form action="university" method="get">
                            <input type="hidden" name="url" value="profile">
                            <input type="hidden" name="studentId" value="${student.id}">
                            <input type="submit" value="Профиль">
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</div>
</body>
</html>