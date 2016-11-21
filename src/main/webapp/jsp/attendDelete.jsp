<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Attend delete</title>
</head>
<body>
<%@include file="menu.jsp"%>

<style>
	.attendDelete{
			position: relative;
            bottom: 70px;
            left: 230px;
	}
</style>

<div class="attendDelete">
<h4>Удалить назначение ?</h4>

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
