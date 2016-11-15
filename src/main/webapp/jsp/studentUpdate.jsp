<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<html>
<head>
    <title>Student update</title>
</head>
<body>
<%@include file="menu.jsp"%>

<c:if test="${sessionScope.firstName ne null}">
    <h4 align="center" style="color:#FF0000">Проверте правильность ввода</h4>
</c:if>

</br>
</br>

<div align="center">
    <form action="university" method="post">
        <input type="hidden" name="url" value="studentUpdateForm">
        <c:choose>
            <c:when test="${sessionScope.studentId ne null}">
                <input type="hidden" name="studentId" value="${sessionScope.studentId}">
            </c:when>
            <c:otherwise>
                <input type="hidden" name="studentId" value="${student.id}">
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${sessionScope.firstName ne null}">
                <h4>Имя</h4> <input type="text" name="firstName" value="${sessionScope.firstName}">
            </c:when>
            <c:otherwise>
                <h4>Имя</h4> <input type="text" name="firstName" value="${student.firstName}">
            </c:otherwise>
        </c:choose>
        <br/><br/>
        <c:choose>
            <c:when test="${sessionScope.lastName ne null}">
                <h4>Фамилия</h4> <input type="text" name="lastName" value="${sessionScope.lastName}">
            </c:when>
            <c:otherwise>
                <h4>Фамилия</h4> <input type="text" name="lastName" value="${student.lastName}">
            </c:otherwise>
        </c:choose>
        <br/><br/>
        <c:choose>
            <c:when test="${sessionScope.entranceYear ne null}">
                <h4>Год поступления</h4> <input type="text" name="entranceYear" value="${sessionScope.entranceYear}">
            </c:when>
            <c:otherwise>
                <h4>Год поступления</h4> <input type="text" name="entranceYear" value="${student.entranceYear}">
            </c:otherwise>
        </c:choose>
        <br/><br/>
        <input type="submit" value="Обновить">
    </form>
</div>
</body>
</html>
