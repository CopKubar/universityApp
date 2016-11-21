<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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

<div class="subjectAdd">
    <form action="university" method="get">
        <input type="hidden" name="url" value="subjectAddForm">
        <p><label><B>Предмет:</B> <input type="text" name="title"> </label></p>
        <input type="submit" value="Добавить предмет">
    </form>
</div>
</body>
</html>
