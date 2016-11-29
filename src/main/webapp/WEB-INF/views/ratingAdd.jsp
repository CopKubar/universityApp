<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Поставить оценку</title>
</head>
<body>
<%@include file="menu.jsp"%>

<style>
    .ratingAdd{
        position: relative;
        bottom: 20px;
        left: 130px;
    }
    .profile{
        position: relative;
        top: 30px;
        left: 10px;
    }
</style>

<div class="profile">
    <a href="<c:url value='/profile/student/${attend.student.id}' />">Профиль</a>
</div>

<div class="ratingAdd">
    <form:form action="ratingSave" method="post" modelAttribute="rating">
        <form:input type="hidden" path="id"/>
        <form:label path="mark">Оценка: </form:label>
        <form:select path="mark" id="mark">
            <form:option value="0" label="Выберите оценку"/>
            <form:options items="${marks}"/>
        </form:select>
        <input type="hidden" name="attendId" value="${attend.id}">
        <input type="submit" value="Поставить">
    </form:form>
</div>
</body>
</html>
