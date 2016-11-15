<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Attend delete</title>
</head>
<body>
<%@include file="menu.jsp"%>

<h4 align="center">Удалить назначение ?</h4>

<div align="center">
    <form action="university" method="post">
        <input type="hidden" name="url" value="attendDeleteForm">
        <input type="hidden" name="attendId" value="${attendId}">
        <input type="radio" name="unswer" value="yes"> Да
        <input type="radio" name="unswer" value="no"> Нет
        <br/><br/>
        <input type="submit" value="Подтвердить">
    </form>
</div>

</body>
</html>
