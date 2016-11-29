<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${student.firstName} ${student.lastName}</title>
</head>
<body>


<style>
    .tableProfile {
        position: relative;
        bottom: 285px;
        left: 270px;
    }
    .actionProfile{
        position: relative;
        top: 25px;
        left: 10px;
    }
    .info{
        position: relative;
        top: 40px;
        left:10px;
    }
</style>

<%@include file="menu.jsp"%>

<div class="actionProfile">
<a href="<c:url value='/student/update/${student.id}' />">Редактировать</a>
</br></br>
<a href="<c:url value='/student/delete/${student.id}' />">Отчислить</a>
</br></br>
<a href="<c:url value='/attend/add/${student.id}' />">Назначить предмет</a>
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
    <h4 colspan="2">Предметы и оценки студента ${student.firstName} ${student.lastName}</h4>
    <table border="2" bgcolor="#C1CDCD">
        <c:forEach items="${student.attends}" var="attend">
            <c:if test="${attend.deleted eq false}">
                <tr>

                    <td align="center">
                        <h3 style="display:inline">${attend.subject.title}</h3>
                    </td>
                    <td align="center">
                        <h4 style="display:inline">Оценки</h4>
                    </td>
                    <c:forEach items="${attend.rating}" var="rating">
                        <c:if test="${rating.deleted eq false}">
                            <td align="center">
                                    ${rating.mark}
                            </td>
                        </c:if>
                    </c:forEach>
                </tr>
                <td align="center">
                    <a href="<c:url value='/attend/delete/${attend.id}' />">Удалить</a>
                </td>
                <td align="center">
                    <a href="<c:url value='/rating/add/${attend.id}' />">Поставить оценку</a>
                </td>
            </c:if>
        </c:forEach>
    </table>
</div>
</body>
</html>

