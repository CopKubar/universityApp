<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
    <%@include file="menu.jsp"%>

</br>
</br>

<!--Add student link-->
<div align="center">
    <form action="university" method="get">
        <input type="hidden" name="url" value="studentAdd">
        <input type="submit" value="Добавить студента">
    </form>
</div>
</br>
</br>

<h4 align="center">Студенты</h4>
<div align="center">
    <table border=1 bgcolor="#FFDEAD">
    <tr>
        <td><B>Имя</B></td>
        <td><B>Фамилия</B></td>
        <td><B>Год поступления</B></td>
    </tr>
    <c:forEach items="${list}" var="student">
        <c:if test="${student.deleted eq false}">
            <tr>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td>${student.entranceYear}</td>
                <td>
                    <form action="university" method="get">
                        <input type="hidden" name="url" value="profile">
                        <c:choose>
                            <c:when test="${sessionScope.studentId ne null}">
                                <input type="hidden" name="studentId" value="${sessionScope.studentId}">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="studentId" value="${student.id}">
                            </c:otherwise>
                        </c:choose>
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