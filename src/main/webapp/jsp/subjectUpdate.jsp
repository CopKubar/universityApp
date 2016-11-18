<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Subject update</title>
</head>
<body>
    <%@include file="menu.jsp"%>

        <div align="center">
            <form action="university" method="post">
                <input type="hidden" name="url" value="subjectUpdateForm">
                <input type="hidden" name="subjectId" value="${subject.id}">
                <h4>Предмет</h4> <input type="text" name="title" value="${subject.title}">
            <br/>
            <input type="submit" value="Редактировать">
            </form>
        </div>

</body>
</html>
