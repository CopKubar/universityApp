<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student profile</title>
</head>
<body>

<%@include file="menu.jsp"%>

<p>
    <h4>Карточка студента :</h4> </br>
    Номер студента: ${student.id}</br>
    Имя: ${student.firstName}</br>
    Фамилия: ${student.lastName}</br>
    Год поступления: ${student.entranceYear}
</p>

<%@include file="actionForStudent.jsp"%>

<div align="center">
    <table border=1 bgcolor="#FFDEAD" cellpadding="3">
        <caption><h4>Предметы и оценки студента ${student.firstName} ${student.lastName}</h4></caption>
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

