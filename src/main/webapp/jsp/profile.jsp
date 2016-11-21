<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student profile</title>
</head>
<body>

<%@include file="menu.jsp"%>

<style>
        .tableProfile {
            position: relative;
            bottom: 310px;
            left: 270px;
        }
		.actionProfile{
            position: relative;
			top: 10px;
            left: 10px;
        }
		.info{
			position: relative;
			top: 70px;
			left:10px;
		}
</style>
	
<div class="actionProfile">
<form action="university" method="get">
    <input type="hidden" name="url" value="studentUpdate">
    <input type="hidden" name="studentId" value=${student.id}>
    <input type="submit" value="Редактировать профиль">
</form>
<form action="university" method="get">
    <input type="hidden" name="url" value="studentDelete">
    <input type="hidden" name="studentId" value=${student.id}>
    <input type="submit" value="Отчислить студента">
</form>
<form action="university" method="get">
    <input type="hidden" name="url" value="attendAdd">
    <input type="hidden" name="studentId" value=${student.id}>
    <input type="submit" value="Назначить предмет студенту">
</form>
</div>
	
<div class="info">
<p>
    <h4>Карточка студента :</h4>
    Номер студента: <B>${student.id}</B></br>
    Имя: <B>${student.firstName}</B></br>
    Фамилия: <B>${student.lastName}</B></br>
    Год поступления: <B>${student.entranceYear}</B>
</p>
</div>

<div class="tableProfile">
    <table border="2" bgcolor="#C1CDCD">
        <th colspan="2">Предметы и оценки студента ${student.firstName} ${student.lastName}</th>
        <c:forEach items="${student.attends}" var="attend">
            <c:if test="${attend.deleted eq false}">
                <tr>

                    <td>
                        <h3 style="display:inline">${attend.subject.title}</h3>
                    </td>
                    <td>
                        <h4 style="display:inline">Оценки</h4>
                    </td>
                    <c:forEach items="${attend.rating}" var="rating">
                        <c:if test="${rating.deleted eq false}">
                            <td>
                                    ${rating.mark}
                            </td>
                        </c:if>
                    </c:forEach>
                </tr>
                <td>
                    <form action="university" method="get">
                        <input type="hidden" name="url" value="attendDelete">
                        <input type="hidden" name="attendId" value=${attend.id}>
                        <input type="submit" value="Удалить назначенный предмет">
                    </form>
                </td>
                <td align="justify">
                    <form action="university" method="get">
                        <input type="hidden" name="url" value="ratingAdd">
                        <input type="hidden" name="attendId" value=${attend.id}>
                        <input type="submit" value="Поставить оценку">
                    </form>
                </td>
            </c:if>
        </c:forEach>
    </table>
</div>
</body>
</html>

