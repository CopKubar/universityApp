<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add subject</title>
</head>
<body>

<%@include file="menu.jsp"%>

<div align="center">
    <form action="university" method="post">
        <input type="hidden" name="url" value="subjectAddForm">
                <h4>Предмет </h4> <input type="text" name="title"> <br/>
        <br/>
        <input type="submit" value="Добавить предмет">
    </form>
</div>

</body>
</html>
