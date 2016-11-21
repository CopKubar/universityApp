<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Subject update</title>
</head>
<body>
    <%@include file="menu.jsp"%>

<style>
	.subjectUpdate{
			position: relative;
            left: 10px;
			top: 60px;
	}
</style>

        <div class="subjectUpdate">
            <form action="university" method="post">
                <input type="hidden" name="url" value="subjectUpdateForm">
                <input type="hidden" name="subjectId" value="${subject.id}">
                <p><label><B>Предмет:</B> <input type="text" name="title" value="${subject.title}"></label></p>
				<input type="submit" value="Редактировать">
            </form>
        </div>
</body>
</html>
