<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add subject</title>
</head>
<body>

<%@include file="menu.jsp"%>

<style>
	.subjectAdd{
			position: relative;
            left: 10px;
			top: 60px;
	}
</style>

<div class="subjectAdd>
<h4 style="color:#FF0000">Проверте правильность ввода</h4>
    <form action="university" method="post">
        <input type="hidden" name="url" value="subjectAddForm">
        <p><label><B>Предмет:</B> <input type="text" name="title" value="${requestScope.title}"> </label></p> <br/>
        <br/>
        <input type="submit" value="Добавить предмет">
    </form>
</div>
</body>
</html>
